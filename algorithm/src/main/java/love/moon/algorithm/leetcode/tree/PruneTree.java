package love.moon.algorithm.leetcode.tree;

public class PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        if ((root.val == 0 && isLeaf(root.left) && isLeaf(root.right)&&root.left.val==0&&root.right.val==0)
                || (isLeaf(root) && root.val == 0)) {
            return null;
        }
        root.left= pruneTree(root.left);
        root.right=pruneTree(root.right);
        if(root.val==0&&root.left==null&&root.right==null){
            return null;
        }
        return root;
    }

    public boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(0, new TreeNode(0), new TreeNode(1));
        TreeNode root = new TreeNode(1);
        root.right = n1;
        PruneTree sol = new PruneTree();
        root = sol.pruneTree(root);
        System.out.println(root);
    }
}
