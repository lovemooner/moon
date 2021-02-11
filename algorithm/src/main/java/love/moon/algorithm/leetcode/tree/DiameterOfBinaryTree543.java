package love.moon.algorithm.leetcode.tree;

/**
 * 543. 二叉树的直径
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * @author : ndong
 * date : 2021/2/12 0:23
 * desc :
 */
public class DiameterOfBinaryTree543 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode root1 = new TreeNode(1, n1, new TreeNode(3));
        DiameterOfBinaryTree543 sol=new DiameterOfBinaryTree543();
        System.out.println(sol.diameterOfBinaryTree(root1));

        TreeNode root2=new TreeNode(1);
        System.out.println(sol.diameterOfBinaryTree(root2));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int left=dept(root.left);
        int right=dept(root.right);
        return left+right;
    }

    public int dept(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left= dept(root.left);
        int right=dept(root.right);
        return Math.max(left,right)+1;
    }
}
