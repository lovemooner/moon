package love.moon.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ndong
 * date : 2020/11/17 19:15
 * desc :
 */
public class InorderTraversal {

    List<Integer> list=new ArrayList();
    public List<Integer> inorderTraversal(TreeNode root) {
        doInorderTraversal(root);
        return list;
    }

    public void doInorderTraversal(TreeNode root) {
        if(root==null){
            return;
        }
        doInorderTraversal(root.left);
        list.add(root.val);
        doInorderTraversal(root.right);
    }


}
