import java.util.*;

public class Kruskal {

    private class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private int operations = 0;

        public String find(String v) {
            operations++;
            if (!parent.containsKey(v)) parent.put(v, v);
            if (!parent.get(v).equals(v)) {
                parent.put(v, find(parent.get(v)));
                operations++;
            }
            return parent.get(v);
        }

        public void union(String a, String b) {
            parent.put(find(a), find(b));
            operations++;
        }

        public int getOperations() {
            return operations;
        }
    }

    public MSTResult findMST(Graph graph) {
        long start = System.currentTimeMillis();

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(e -> e.weight));

        UnionFind uf = new UnionFind();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        for (Edge e : edges) {
            operations++;
            if (!uf.find(e.source).equals(uf.find(e.target))) {
                uf.union(e.source, e.target);
                mstEdges.add(e);
                totalCost += e.weight;
                operations++;
            }
        }

        long end = System.currentTimeMillis();
        operations += uf.getOperations();

        return new MSTResult(mstEdges, totalCost, end - start, operations);
    }
}
