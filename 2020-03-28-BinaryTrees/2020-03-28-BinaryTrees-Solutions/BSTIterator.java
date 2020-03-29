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


class BSTIterator {

    private Stack<TreeNode>  stack;
    public BSTIterator(TreeNode root){
        stack = new Stack<TreeNode>();
        TreeNode cur = root;
        // go to the left-most leaf, while pushing every element onto the stack along the way
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
    }

    public int next(){
        if(hasNext()){
            TreeNode next_element = stack.pop();            
            if(next_element.right != null){
                TreeNode cur = next_element.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            return next_element.val;
        }
        else throw new RuntimeException("no elements remaining");
    }

    public boolean hasNext(){
        return !stack.empty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
