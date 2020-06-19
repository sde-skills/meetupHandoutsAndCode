/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        
        helper(root, 0, list);
        
        return list;
    }
    
    public void helper(TreeNode root, int level, List<Integer> list) {
        
        if(root == null)
            return;
        
        //only add when seen the level for first time
        if(list.size() == level)
            list.add(root.val);
        
        helper(root.right, level + 1, list);
        helper(root.left, level + 1, list);
        
    }
    
    
}