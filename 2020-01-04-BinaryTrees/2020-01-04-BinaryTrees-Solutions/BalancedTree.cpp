/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:

    int abs(int i)
    {
          return i < 0 ? -i : i;
    }
    
    bool isBalancedHelper(TreeNode* root, int & height){
        if (root == nullptr)
        {
            height = -1;
            return true;
        }
        
        int leftHeight;
        int rightHeight;
        if(isBalancedHelper(root->left, leftHeight) && 
           isBalancedHelper(root->right, rightHeight) && 
           abs(leftHeight - rightHeight) <= 1)
        {
            height = 1 + max(leftHeight, rightHeight);
            return true;   
        }
        
        return false;
    }
    
    bool isBalanced(TreeNode* root) {
        int height;
        return isBalancedHelper(root, height);
    }
};
