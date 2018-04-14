package tbs.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TBSSalesReportFormatter implements SalesReportFormatter {

    private static final String REPORT_LINE_FORMAT = "%s\t%s\t%d\t$%d";

    private final TBSServerState state;

    public TBSSalesReportFormatter(TBSServerState state) {
        this.state = Objects.requireNonNull(state);
    }

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

    protected String formatReportLine(String performanceId, String startTime, int ticketsSold, int salesReceipts) {
        return String.format(REPORT_LINE_FORMAT, performanceId, startTime, ticketsSold, salesReceipts);
    }
}
