package love.moon.algorithm.leetcode.tree;

/**
 * @author : ndong
 * date : 2021/2/12 21:08
 * desc :
 */
public class ConvertBST538 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(7, null, new TreeNode(8));
        TreeNode n2 = new TreeNode(6, new TreeNode(5), n1);

        TreeNode n3 = new TreeNode(2, null, new TreeNode(3));
        TreeNode n4 = new TreeNode(1, new TreeNode(0), n3);
        TreeNode root = new TreeNode(4, n4, n2);
        ConvertBST538 sol = new ConvertBST538();
        sol.convertBST(root);
        System.out.println(root);

    }


    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

}
