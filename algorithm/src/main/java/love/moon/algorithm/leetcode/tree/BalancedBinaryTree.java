package love.moon.algorithm.leetcode.tree;

import love.moon.algorithm.leetcode.tree.TreeNode;

/**
 * @author : ndong
 * date : 2020/11/9 18:42
 * desc :
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }


}
