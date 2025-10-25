import java.util.*;

public class Graph {
    private Set<String> vertices;
    private List<Edge> edges;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new ArrayList<>();
    }

    public void addEdge(String source, String target, int weight) {
        edges.add(new Edge(source, target, weight));
        vertices.add(source);
        vertices.add(target);
    }

    public void addVertex(String v) {
        vertices.add(v);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<String> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "Graph(V=" + vertices.size() + ", E=" + edges.size() + ")";
    }
}
