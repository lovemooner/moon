package love.moon.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelOrder {


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(20, new TreeNode(15), new TreeNode(17));
        TreeNode root = new TreeNode(3);
        root.right = n1;
        root.left=new TreeNode(9);
        LevelOrder sol = new LevelOrder();
        List<List<Integer>>  lists = sol.levelOrder(root);
        System.out.println(lists.size());
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer,List<Integer>> map=new HashMap<>();
        inOrder(root,0,map);
        return new ArrayList(map.values());
    }

    public void inOrder(TreeNode node,int level,Map<Integer,List<Integer>>map){
        if(node==null){
            return;
        }
        if(map.get(level)==null) map.put(level,new ArrayList<>());
        List<Integer> list= map.get(level);
        list.add(node.val);
        inOrder(node.left,level+1,map);
        inOrder(node.right,level+1,map);
    }

}
