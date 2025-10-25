import com.google.gson.*;
import java.io.*;
import java.util.*;

public class MSTMain {
    public static void main(String[] args) {
        String inputPath = "src/main/resources/assign_3_input.json";
        String outputPath = "src/main/resources/assign_3_output.json";

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader(inputPath);
            JsonObject jsonInput = gson.fromJson(reader, JsonObject.class);
            reader.close();

            JsonArray graphsArray = jsonInput.getAsJsonArray("graphs");
            List<JsonObject> results = new ArrayList<>();

            for (JsonElement gElem : graphsArray) {
                JsonObject gObj = gElem.getAsJsonObject();
                int graphId = gObj.get("id").getAsInt();

                Graph graph = new Graph();
                JsonArray edges = gObj.getAsJsonArray("edges");
                for (JsonElement eElem : edges) {
                    JsonObject eObj = eElem.getAsJsonObject();
                    String from = eObj.get("from").getAsString();
                    String to = eObj.get("to").getAsString();
                    int weight = eObj.get("weight").getAsInt();
                    graph.addEdge(from, to, weight);
                }

                Prim prim = new Prim();
                Kruskal kruskal = new Kruskal();

                MSTResult primResult = prim.findMST(graph);
                MSTResult kruskalResult = kruskal.findMST(graph);

                JsonObject resObj = new JsonObject();
                resObj.addProperty("graph_id", graphId);

                JsonObject inputStats = new JsonObject();
                inputStats.addProperty("vertices", graph.getVertices().size());
                inputStats.addProperty("edges", graph.getEdges().size());
                resObj.add("input_stats", inputStats);

                resObj.add("prim", resultToJson(primResult));
                resObj.add("kruskal", resultToJson(kruskalResult));

                results.add(resObj);
            }

            JsonObject output = new JsonObject();
            output.add("results", gson.toJsonTree(results));
            Writer writer = new FileWriter(outputPath);
            gson.toJson(output, writer);
            writer.close();

            System.out.println("Results saved to: " + outputPath);

        } catch (Exception e) {
            System.out.println("Mistake:" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static JsonObject resultToJson(MSTResult result) {
        JsonObject obj = new JsonObject();
        JsonArray mstEdges = new JsonArray();
        for (Edge e : result.getEdges()) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", e.source);
            edgeObj.addProperty("to", e.target);
            edgeObj.addProperty("weight", e.weight);
            mstEdges.add(edgeObj);
        }
        obj.add("mst_edges", mstEdges);
        obj.addProperty("total_cost", result.getTotalCost());
        obj.addProperty("operations_count", result.getOperationsCount());
        obj.addProperty("execution_time_ms", result.getTimeMs());
        return obj;
    }
}
