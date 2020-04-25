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
    
    // Path Sum 1     
    public boolean hasPathSum(TreeNode root, int target) {
        
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == target) return true;
        
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
            
             
    }

     // Path Sum 2 Helper
     public void pathSum2Helper(TreeNode root, int target, List<List<Integer>> output, List<Integer> curPath){
        
        if(root == null) return;
        
        curPath.add(root.val);
        if(root.left == null && root.right == null && root.val == target){
            output.add(new ArrayList<Integer>(curPath));
            curPath.remove(curPath.size() - 1);
            return;
        }
        
        pathSum2Helper(root.left, target - root.val, output, curPath);
        pathSum2Helper(root.right, target - root.val, output, curPath);
        
        curPath.remove(curPath.size() - 1);
        
    }
   
    // Path Sum 2 
    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        var output = new ArrayList<List<Integer>>();
        pathSum2Helper(root, target, output, new ArrayList<Integer>());
        return output;
    }

     // Path Sum 3
     public int pathSum3Helper(TreeNode root, int target){
        if(root == null) return 0;
        
        var paths = 0;
        
        if(root.val == target) { paths += 1; }
        
        paths += pathSum3Helper(root.left, target - root.val);
        paths += pathSum3Helper(root.right, target - root.val);
        
        return paths;
    }
        
    
    public int pathSum3(TreeNode root, int sum) {
        
        if(root == null) return 0;
        return pathSum3Helper(root, sum) + pathSum3(root.left, sum) + pathSum3(root.right, sum);
        
    }










}
