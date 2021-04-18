package love.moon.algorithm.graph;

import love.moon.algorithm.graph.base.GraphEdge;
import love.moon.algorithm.graph.base.GraphNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://blog.csdn.net/lemon_tree12138/article/details/47319659
 */
public class GraphByMatrix {
    private List<GraphNode> nodes = new ArrayList<>();

    public static void main(String[] args) {
        GraphByMatrix sol = new GraphByMatrix();
        sol.initGraph(8);
        //起点
        GraphNode startNode = sol.getGraphNodes().get(0);
        List<GraphNode> visited = new ArrayList<>();
        sol.dfs(startNode, visited);
    }



    /**
     * 深度遍历
     * @param node    当前节点
     * @param visited 被访问过的节点列表
     */
    public void dfs(GraphNode node, List<GraphNode> visited) {
        if (visited.contains(node)) { // 判断是否遍历过
            return;
        }
        visited.add(node);
        System.out.println("节点：" + node.getLabel());
        for (int i = 0; i < node.edgeList.size(); i++) {
            dfs(node.edgeList.get(i).getNodeRight(), visited);
        }
    }


    /**
     * 广度优先搜索
     *
     * @param node 搜索的入口节点
     */
    public void searchTraversing(GraphNode node) {
        List<GraphNode> visited = new ArrayList<>(); // 已经被访问过的元素
        Queue<GraphNode> q = new LinkedList<>(); // 用队列存放依次要遍历的元素
        q.offer(node);

        while (!q.isEmpty()) {
            GraphNode currNode = q.poll();
            if (!visited.contains(currNode)) {
                visited.add(currNode);
                System.out.println("节点：" + currNode.getLabel());
                for (int i = 0; i < currNode.edgeList.size(); i++) {
                    q.offer(currNode.edgeList.get(i).getNodeRight());
                }
            }
        }
    }

    public void initGraph(int n) {
        for (int i = 0; i < n; i++) {
            GraphNode node = new GraphNode(String.valueOf(i));
            nodes.add(node);
        }
        initGraph(n, false);
    }


    public void initGraph(int n, boolean b) {
        GraphEdge edge01 = new GraphEdge(nodes.get(0), nodes.get(1));
        GraphEdge edge02 = new GraphEdge(nodes.get(0), nodes.get(2));
        GraphEdge edge13 = new GraphEdge(nodes.get(1), nodes.get(3));
        GraphEdge edge14 = new GraphEdge(nodes.get(1), nodes.get(4));
        GraphEdge edge25 = new GraphEdge(nodes.get(2), nodes.get(5));
        GraphEdge edge26 = new GraphEdge(nodes.get(2), nodes.get(6));
        GraphEdge edge37 = new GraphEdge(nodes.get(3), nodes.get(7));
        GraphEdge edge47 = new GraphEdge(nodes.get(4), nodes.get(7));
        GraphEdge edge56 = new GraphEdge(nodes.get(5), nodes.get(6));

        nodes.get(0).addEdgeList(edge01);
        nodes.get(0).addEdgeList(edge02);
        nodes.get(1).addEdgeList(edge13);
        nodes.get(1).addEdgeList(edge14);
        nodes.get(2).addEdgeList(edge25);
        nodes.get(2).addEdgeList(edge26);
        nodes.get(3).addEdgeList(edge37);
        nodes.get(4).addEdgeList(edge47);
        nodes.get(5).addEdgeList(edge56);
        System.out.println("init done");
    }


    public List<GraphNode> getGraphNodes() {
        return nodes;
    }
}
