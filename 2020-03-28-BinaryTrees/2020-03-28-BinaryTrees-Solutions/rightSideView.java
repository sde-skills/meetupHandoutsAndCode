import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}


class Solution {
    public List<Integer> rightSideView(TreeNode root) {
     
        List<Integer> output = new ArrayList<Integer>();

        if(root == null) return output;

        Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();

        // if we use a queue instead of a stack, we get the left side view.
        Stack<Integer> curLevel = new Stack<Integer>();
        bfsQueue.add(root);
        bfsQueue.add(null);
        TreeNode prev = null;
        while(!bfsQueue.isEmpty()){
            TreeNode cur = bfsQueue.remove();

            if(cur == null){
                if(prev == null) break;
                if(!curLevel.isEmpty()){
                    output.add(curLevel.pop());
                }
                bfsQueue.add(null);
                curLevel = new Stack<Integer>();
                prev = cur;
                continue;
            }

            curLevel.push(cur.val);
            if(cur.left != null)
                bfsQueue.add(cur.left);
            if(cur.right != null)
                bfsQueue.add(cur.right);
            prev = cur;

        }

        return output;
        
    }
}
