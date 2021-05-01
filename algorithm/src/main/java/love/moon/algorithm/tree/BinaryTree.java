package love.moon.algorithm.tree;

import love.moon.algorithm.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 二叉查找树
 * <p>
 * Author: lovemooner
 * Date: 2017/6/2 12:20
 */
public class BinaryTree {

    public TreeNode root = null;

    public BinaryTree(int[] array, int index) {
        root = createBinaryTree(array, index);
    }

    public static void main(String[] args) {
        //顺序存储的满二叉树或者完全二叉树
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinaryTree bt = new BinaryTree(arr, 0);
        System.out.println("递归-前序遍历：");
        bt.preOrder(bt.root);
        System.out.println();
        System.out.println("递归-中序遍历：");
        bt.inOrder(bt.root);
        System.out.println();
        System.out.println("递归-后序遍历：");
        bt.postOrder(bt.root);
//        System.out.println();
//        System.out.println("非递归-前序遍历：");
//        bt.noRecursionPreOrder(bt.root);
//        System.out.println();
//        System.out.println("非递归-中序遍历：");
//        bt.noRecursionInOrder(bt.root);
//        System.out.println();
//        System.out.println("非递归-后序遍历：");
//        bt.noRecursionPostOrder(bt.root);
    }

    /**
     * 创建二叉树
     *
     * @param array
     * @param index
     * @return
     */
    private TreeNode createBinaryTree(int[] array, int index) {
        TreeNode node = null;
        if (index < array.length) {
            node = new TreeNode(array[index]);
            // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
            node.left = createBinaryTree(array, 2 * index + 1);
            node.right = createBinaryTree(array, 2 * index + 2);
        }
        return node;
    }

    /**
     * 递归遍历二叉树-先序遍历（前序遍历）
     *
     * @param node
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 递归遍历二叉树-中序遍历
     *
     * @param node
     */
    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.val);
            inOrder(node.right);
        }
    }

    /**
     * 递归遍历二叉树-后序遍历
     *
     * @param node
     */
    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            showData(node);
        }
    }

    // 非递归遍历二叉树

    // 前序遍历

    public void noRecursionPreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                showData(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }

            }
        }
    }

    // 中序遍历
    public void noRecursionInOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                showData(p);
                p = p.right;
            }
        }
    }

    //后序遍历 ,需要记录遍历过的节点
    public void noRecursionPostOrder(TreeNode node) {
        TreeNode pre = node;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null) {
            for (; node.left != null; node = node.left) {
                stack.push(node);
            }
            while (node != null && (node.right == null || node.right == pre)) {
                showData(node);
                pre = node;
                if (stack.empty()) return;
                node = stack.pop();
            }
            stack.push(node);
            node = node.right;
        }
    }

    private void showData(TreeNode node) {
        System.out.print(node.val);
    }

}