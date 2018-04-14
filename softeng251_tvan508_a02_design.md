Description of domain model & Justification
===========================================

Concepts and Relationships
--------------------------

* Act
    - Has a title, a duration in minutes and a unique ID.
    - Has one Artist.
    - Can have any number of performances.
    - Represented by the `Artist` class.
* Artist
    - Has a name and unique ID.
    - Can perform any number of acts.
* Performance
    - Has a start time, and pricing for both premium and 'cheap' seats.
    - Takes place in any one theatre.
    - Is a performance of one act.
    - Has tickets issued, up to a maximum of one for each seat in the theatre.
* Seat
* Server
* Theatre
* Theatre file 
* Sales report
* Ticket

Justification
-------------

The design is my best attempt at modelling the above domain model. Below I have noted
particular design choices I have made that I think are interesting and might
warrant discussion.

**Identifiable**

Almost all entities in the domain model (Act, Artist, Performance, Theatre, Ticket, etc...)
have a unique ID. The `Identifiable` interface allows 

**IdentifiableRepository\<T extends Identifiable\> and subclasses**

There are quite a few common operations on collections of identifiable objects specified in TBSServer 
(e.g. `TBSServer#getTheatreIDs()`, `TBSServer#getArtistIDs()`). An `IdentifiableRepository` is a collection which
stores `Identifiable` objects of type `T`, and has features including:

* Does not allow for storing of objects with duplicate IDs
* Has a `getAllIDs` method to quickly get all IDs in the repository

There are several subclasses to `IdentifiableRepository` (such as `ActRepository`), which add type-specific
features to the base repository. For example, `ActRepository` has a `getArtistNames()` method to fetch the names
of all artists in the repository. This is used in `TBSServerImpl#getArtistNames()`. Having these methods in the
collection types removes responsibility from `TBSServerImpl` into a more reasonable place.

**IDGenerator**

The creation and management of unique IDs is the responsibility of the `IDGenerator` interface. It has a `createUniqueId()`
method, used to fetch a new unique ID, and is primarily used when creating new `Identifiable` objects. To ensure ID uniqueness,
most `Identifiable` objects take an `IDGenerator` parameter in their constructor. `IDGenerator` also has an `isValidID(String)` helper
method which is used to check if an ID is of the correct format. I have included 3 `IDGenerator` implementations: `UUIDIDGenerator`,
which creates a random UUID for every object (UUIDs are called universally unique for a good reason), `AutoIncrementIDGenerator`,
which increments a counter every time a new ID is generated and returns that counter as the ID, and lastly `CustomIDGenerator` which
allows for users to specify which ID should be generated next while ensuring uniqueness. `CustomIDGenerator` is used by
`TheatreParser` when reading theatres from the file, while `AutoIncrementIDGenerator` is used for everything else. `UUIDIDGenerator`
is currently unused.

**TBSException**

The whole idea of returning "ERROR <reason>" when there's a problem is rather arbitrary and I'd rather that doesn't
occur anywhere in my code other than `TBSServerImpl`. If there's a problem anywhere, the code will throw `TBSException`
which is then caught by `TBSServerImpl`. `TBSServerImpl` then outputs `"ERROR" + e.getMessage()`. `TBSException` is
unchecked, the only reason for this is that I find checked exceptions annoying.

**SalesReportFormatter**

The concept of a "sales report" is independent of the data model itself, and so I've abstracted this away using a
`SalesReportFormatter` interface. It generates a sales report (as a list of `String`) for a given act. The implementation
for this is provided by `TBSSalesReportFormatter` which formats the sales report as required for `TBSServer`.

**TBSServerState**

The notion of the server's state is independent of the `TBSServer` interface thus I have moved this into a separate
`TBSServerState` interface and implementing class, `MemoryTBSServerState`. In theory, with significant redesign of
`IdentifiableRepository` and its subclasses, it could be possible to create an implementation of `TBSServerState` which
is not backed by memory but rather a database or similar. However, this is out of scope for the assignment.

**Use of `static`**

The use of `static` is limited to constants and helper methods (which do not affect application state). The three helper
methods are:
* `TBSServerImpl#error(String)`: Creates a string with an error message (just prepends "ERROR") to the message;
* `TBSServerImpl#listError(String)`: As above, but with the return type as a singleton list of String;
* `Performance#parsePriceString(String)`: 