package love.moon.algorithm.leetcode.tree;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。最近公共祖先的定义为：
 * “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *
 * @author : ndong
 * date : 2021/2/12 22:33
 * desc :
 */
public class LowestCommonAncestor236 {

    TreeNode target;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor(null,root,p,q);
          return null;
    }
    public TreeNode lowestCommonAncestor(TreeNode parent,TreeNode root, TreeNode p, TreeNode q) {
        if(root.val==p.val){
            if (findQ(root,q)){
             target=root;
            }else {
                findQ(parent,q);
            }
        }
        TreeNode left= lowestCommonAncestor(root,root.left,p,q);
        TreeNode right=lowestCommonAncestor(root,root.right,p,q);
        if(right!=null&&right.val==q.val){
            target=left;
        }
        lowestCommonAncestor(parent,root,p,q);
        return null;
    }

    public boolean findQ(TreeNode root,TreeNode q) {
        if(root.val==q.val){
            return true;
        }
        return findQ(root.left,q)||findQ(root.right,q);
    }




}
