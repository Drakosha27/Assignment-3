import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimKruskalPerformanceTest {

    @Test
    public void testExecutionTimeAndOperations() {
        Graph g = new Graph();
        for (int i = 1; i <= 6; i++) {
            for (int j = i + 1; j <= 6; j++) {
                g.addEdge("V" + i, "V" + j, (i + j) % 10 + 1);
            }
        }

        Prim prim = new Prim();
        Kruskal kruskal = new Kruskal();

        MSTResult primRes = prim.findMST(g);
        MSTResult kruskalRes = kruskal.findMST(g);

        assertTrue(primRes.getTimeMs() >= 0);
        assertTrue(kruskalRes.getTimeMs() >= 0);
        assertTrue(primRes.getOperationsCount() >= 0);
        assertTrue(kruskalRes.getOperationsCount() >= 0);

        MSTResult primRes2 = prim.findMST(g);
        MSTResult kruskalRes2 = kruskal.findMST(g);
        assertEquals(primRes.getTotalCost(), primRes2.getTotalCost());
        assertEquals(kruskalRes.getTotalCost(), kruskalRes2.getTotalCost());
    }
}
