public class Edge {
    public String source;
    public String target;
    public int weight;

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source + " - " + target + " (" + weight + ")";
    }
}
