package love.moon.algorithm.graph.base;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private String label;
    public List<GraphEdge> edgeList;

    public GraphNode(String label) {
        this.label = label;
        if (edgeList == null) {
            edgeList = new ArrayList<>();
        }
    }

    /**
     * 给当前节点添加一条边
     * GraphNode
     * @param edge 添加的边
     */
    public void addEdgeList(GraphEdge edge) {
        edgeList.add(edge);
    }

    public String getLabel() {
        return label;
    }
}
