package love.moon.algorithm.leetcode.tree;

/**
 * @author : ndong
 * date : 2020/11/17 19:52
 * desc :
 */
public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        return traversal(root,null);
    }

    public int traversal(TreeNode node,String from){
        if(node==null){
            return 0;
        }
        if(node!=null&&node.left==null){
            return node.left.val;
        }
        int lLeaf= traversal(node.left,"L");
        int lReaf= traversal(node.left,"R");
        return lLeaf+lReaf;
    }


}
