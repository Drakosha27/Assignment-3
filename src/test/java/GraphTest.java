import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    public void testAddEdge() {
        Graph g = new Graph();
        g.addEdge("A", "B", 5);
        g.addEdge("B", "C", 3);
        assertEquals(2, g.getEdges().size());
        assertTrue(g.getVertices().contains("A"));
        assertTrue(g.getVertices().contains("B"));
        assertTrue(g.getVertices().contains("C"));
    }
}
