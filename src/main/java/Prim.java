import java.util.*;

public class Prim {

    public MSTResult findMST(Graph graph) {
        long start = System.currentTimeMillis();

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        if (graph.getVertices().isEmpty()) {
            return new MSTResult(mstEdges, 0, 0, 0);
        }

        String startVertex = graph.getVertices().iterator().next();
        visited.add(startVertex);

        while (visited.size() < graph.getVertices().size()) {
            Edge minEdge = null;
            for (Edge e : graph.getEdges()) {
                operations++;
                if (visited.contains(e.source) && !visited.contains(e.target) ||
                        visited.contains(e.target) && !visited.contains(e.source)) {
                    if (minEdge == null || e.weight < minEdge.weight) {
                        minEdge = e;
                        operations++;
                    }
                }
            }

            if (minEdge == null) break;

            mstEdges.add(minEdge);
            totalCost += minEdge.weight;
            visited.add(minEdge.source);
            visited.add(minEdge.target);
            operations++;
        }

        long end = System.currentTimeMillis();
        return new MSTResult(mstEdges, totalCost, end - start, operations);
    }
}
