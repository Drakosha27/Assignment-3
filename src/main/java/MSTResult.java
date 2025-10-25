import java.util.List;

public class MSTResult {
    private List<Edge> edges;
    private int totalCost;
    private long timeMs;
    private int operationsCount;

    public MSTResult(List<Edge> edges, int totalCost, long timeMs, int operationsCount) {
        this.edges = edges;
        this.totalCost = totalCost;
        this.timeMs = timeMs;
        this.operationsCount = operationsCount;
    }

    public List<Edge> getEdges() { return edges; }
    public int getTotalCost() { return totalCost; }
    public long getTimeMs() { return timeMs; }
    public int getOperationsCount() { return operationsCount; }
}
