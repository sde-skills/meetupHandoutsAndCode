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
    vector<vector<int>> zigzagLevelOrder(TreeNode *root) {
        vector<vector<int>> result;

        // Tree is empty
        if (root == nullptr) {
          return result;
        }

        queue<TreeNode *> queue;
        queue.push(root);

        // Tell zigzag traversal direction
        bool leftToRight = true;

        // Walk through level by level for populating current level array and preparing next level
        while (!queue.empty()) {
            int n = queue.size();
            vector<int> levelValues(n);

            // The loop will populate an array of current level items and will add next level items
            for (int i = 0; i < n; i++) {
                TreeNode *node = queue.front();
                queue.pop();

                // Add current node's value based on traversal direction
                if (leftToRight) {
                  levelValues[i] = node->val;
                } else {
                  levelValues[n - 1 - i] = node->val;
                }

                // Enqueue the children of current node
                if (node->left != nullptr) {
                  queue.push(node->left);
                }
                if (node->right != nullptr) {
                  queue.push(node->right);
                }
        }

        // Add curreLevel into result & Toggle the traversal direction
        result.push_back(levelValues);
        leftToRight = !leftToRight;
    }

    return result;
  }
};


