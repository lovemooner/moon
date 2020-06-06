package love.moon.algorithm.tree;

import java.util.Stack;

/**
 * Author: lovemooner
 * Date: 2017/6/2 12:20
 */
public class BinaryTree {

    /**
     * 递归先序遍历
     * */
    public void preOrderRecursion(TreeNode node){
        if(node==null) //如果结点为空则返回
            return;
        visit(node);//访问根节点
        preOrderRecursion(node.left);//访问左孩子
        preOrderRecursion(node.right);//访问右孩子
    }

    public void visit(TreeNode node){
        System.out.print(node.val+" ");
    }

}