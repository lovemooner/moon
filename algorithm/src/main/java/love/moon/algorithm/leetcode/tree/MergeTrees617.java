package love.moon.algorithm.leetcode.tree;

/**
 * @author : ndong
 * date : 2021/2/11 18:17
 * desc :
 */
public class MergeTrees617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null&&root2==null) return null;
        if(root1==null) root1=new TreeNode();
        root1.val=root1.val+(root2!=null?root2.val:0);
        root1.left= mergeTrees(root1.left,root2!=null?root2.left:null);
        root1.right=mergeTrees(root1.right,root2!=null?root2.right:null);
        return root1;
    }

}
