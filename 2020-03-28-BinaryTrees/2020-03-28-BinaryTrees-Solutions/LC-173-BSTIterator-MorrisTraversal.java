/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    
    TreeNode curr;

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    /** @return the next smallest number */
    public int next() {        
        TreeNode res = null;
        
        while(curr != null) {            
            if(curr.left == null) {
                res = curr;
                curr = curr.right;
                break;
            } else {
                //find predessor of curr, which would be the left node and then traverse till right child is null. 
                //Or right child is actually the current
                TreeNode temp = curr.left;
                while(temp.right != null && temp.right != curr ) {
                    temp = temp.right;
                }
                if(temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                } else {
                    res = curr;
                    temp.right = null;
                    curr = curr.right;       
                    break;
                }                
            }
        }
        return res.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return curr != null;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */