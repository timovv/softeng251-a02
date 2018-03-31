package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class TBSSalesReportFormatter implements SalesReportFormatter {

    private static final String REPORT_LINE_FORMAT = "%s\t%s\t%d\t%d\n";

    private final TBSServerState state;

    public TBSSalesReportFormatter(TBSServerState state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> formatSalesReport(Act act) {
        List<String> output = new ArrayList<>();
        for(Performance performance : state.getPerformanceRepository().getPerformancesForAct(act)) {
            String performanceId = performance.getId();
            String startTime = performance.getStartTime();

            List<Ticket> ticketList = performance.getIssuedTickets();
            int ticketsSold = ticketList.size();

            int salesReceipts = 0;
            for(Ticket ticket : ticketList) {
                salesReceipts += ticket.getPrice();
            }

            output.add(formatReportLine(performanceId, startTime, ticketsSold, salesReceipts));
        }

        return output;
    }

    /**
     * Given the parameters, format one line in the sales report (corresponding to one performance). This method
     * may be overridden to change the sales report format.
     * @param performanceId The id of the performance.
     * @param startTime The start time of the performance as a string.
     * @param ticketsSold The number of tickets sold for the performance.
     * @param salesReceipts The total sales receipts for the performance.
     * @return The formatted line of the sales report for the performance.
     */
    protected String formatReportLine(String performanceId, String startTime, int ticketsSold, int salesReceipts) {
        return String.format(REPORT_LINE_FORMAT, performanceId, startTime, ticketsSold, salesReceipts);
    }
}
