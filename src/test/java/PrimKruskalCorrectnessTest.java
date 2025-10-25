import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PrimKruskalCorrectnessTest {

    private boolean isConnected(Graph g, List<Edge> mstEdges) {
        Map<String, List<String>> adj = new HashMap<>();
        for (Edge e : mstEdges) {
            adj.computeIfAbsent(e.source, k -> new ArrayList<>()).add(e.target);
            adj.computeIfAbsent(e.target, k -> new ArrayList<>()).add(e.source);
        }
        if (adj.isEmpty()) return false;
        Set<String> visited = new HashSet<>();
        String start = adj.keySet().iterator().next();
        dfs(start, adj, visited);
        return visited.containsAll(g.getVertices());
    }

    private void dfs(String node, Map<String, List<String>> adj, Set<String> visited) {
        visited.add(node);
        for (String nb : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(nb)) dfs(nb, adj, visited);
        }
    }

    private boolean hasCycle(List<Edge> edges) {
        Map<String, String> parent = new HashMap<>();
        for (Edge e : edges) {
            String root1 = find(parent, e.source);
            String root2 = find(parent, e.target);
            if (root1.equals(root2)) return true;
            parent.put(root1, root2);
        }
        return false;
    }

    private String find(Map<String, String> parent, String v) {
        parent.putIfAbsent(v, v);
        if (!parent.get(v).equals(v))
            parent.put(v, find(parent, parent.get(v)));
        return parent.get(v);
    }

    @Test
    public void testPrimAndKruskalSameTotalCost() {
        Graph g = new Graph();
        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 3);
        g.addEdge("B", "C", 2);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 7);

        Prim prim = new Prim();
        Kruskal kruskal = new Kruskal();

        MSTResult primResult = prim.findMST(g);
        MSTResult kruskalResult = kruskal.findMST(g);

        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost());
        assertEquals(g.getVertices().size() - 1, primResult.getEdges().size());
        assertEquals(g.getVertices().size() - 1, kruskalResult.getEdges().size());
        assertFalse(hasCycle(primResult.getEdges()));
        assertFalse(hasCycle(kruskalResult.getEdges()));
        assertTrue(isConnected(g, primResult.getEdges()));
        assertTrue(isConnected(g, kruskalResult.getEdges()));
    }

    @Test
    public void testDisconnectedGraphHandled() {
        Graph g = new Graph();
        g.addEdge("A", "B", 3);
        g.addVertex("C");

        Prim prim = new Prim();
        MSTResult result = prim.findMST(g);

        assertTrue(result.getEdges().size() < g.getVertices().size() - 1);
    }
}
