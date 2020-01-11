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
    
    TreeNode* buildTreeHelper(vector<int>& preorder, int preStart, int preEnd, vector<int>& inorder, int inStart, int inEnd, unordered_map<int, int>& lookup){
        if (inStart > inEnd)
            return NULL;
        
        int rootData = preorder[preStart];
        TreeNode* root = new TreeNode(rootData);
        int rootIndex = lookup[rootData];
        
        // Prepare left and right subtree's Inorder index
        int leftInStart = inStart;
        int leftInEnd = rootIndex -1;
        int rightInStart = rootIndex + 1;
        int rightInEnd = inEnd;
        
        // Prepare left and right subtree's Preorder index
        int leftPreStart = preStart + 1;
        int leftPreEnd = leftInEnd - leftInStart + leftPreStart;
        int rightPreStart = leftPreEnd + 1;
        int rightPreEnd = preEnd;
        
        root->left = buildTreeHelper(preorder, leftPreStart, leftPreEnd, inorder, leftInStart, leftInEnd, lookup);
        root->right = buildTreeHelper(preorder, rightPreStart, rightPreEnd, inorder, rightInStart, rightInEnd, lookup);
        
        return root;
    }
    
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int n = preorder.size();
        unordered_map<int, int> lookup;
        for(auto i=0; i<inorder.size(); i++)
        {
            lookup.emplace(inorder[i], i);
        }
        
        return buildTreeHelper(preorder, 0, n-1, inorder, 0, n-1, lookup);
    }
};
