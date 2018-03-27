package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class PerformanceRepository extends IdentifiableRepository<Performance> {

    public List<String> getPerformancesIDsForAct(Act act) {
        List<String> output = new ArrayList<>();
        for(Performance performance : getAllValues()) {
            if(performance.getAct().equals(act)) {
                output.add(performance.getId());
            }
        }

        return output;
    }

    public List<Performance> getPerformancesForAct(Act act) {
        List<Performance> output = new ArrayList<>();
        for(Performance performance : getAllValues()) {
            if(performance.getAct().equals(act)) {
                output.add(performance);
            }
        }

        return output;
    }
}
