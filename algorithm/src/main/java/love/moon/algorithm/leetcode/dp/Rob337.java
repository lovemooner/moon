package love.moon.algorithm.leetcode.dp;

import love.moon.algorithm.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ndong
 * date : 2021/5/1 14:49
 * desc :
 */
public class Rob337 {

    Map<TreeNode, Integer> f = new HashMap<>(); //节点选中：f(o)=g(l)+g(r)
    Map<TreeNode, Integer> g = new HashMap<>(); //节点不选中：g(o) = max{f(l),g(l)} + max{ f(r) ,g(r)}


    public int Rob337(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val+g.getOrDefault(node.left, 0) +g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
}
