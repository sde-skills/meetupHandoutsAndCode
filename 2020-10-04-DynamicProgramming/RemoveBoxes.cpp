/************************************ Recursion ***************************************/
class Solution0 {
    // (0) Brute Force
    int helper(vector<int>& boxes)
    {
        int n = boxes.size();
        if (n <= 0) return 0;
        int ans = 0;
        for(int i = 0, j = i+1; i < n; i++)
        {
            while(j < n && boxes[i] == boxes[j]) j++;
            
            vector<int> remainBoxes;
            
            for(int idx = 0; idx < n; idx++)
            {
                if (idx == i) idx = j;
                if (idx < n) remainBoxes.push_back(boxes[idx]);
            }
            
            int k = j - i;
            ans = max(ans, helper(remainBoxes) + (k * k));
        }
        return ans;
    }
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        
        return helper(boxes);
    }
};

class Solution1 {
    // (1) Brute force - 2
    int helper(vector<int> boxes, int i, int j, int k) 
    {
        if (i > j) return 0;
       
        // all boxes of the same color continuously should be grouped together
        for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++);
        
        //case 1:
        int res = (k + 1) * (k + 1) + helper(boxes, i + 1, j, 0);

        //case 2:
        for (int m = i + 1; m <= j; m++) 
        {
            if (boxes[i] == boxes[m])
            {   
                res = max(res, helper(boxes, i + 1, m - 1, 0) + helper(boxes, m, j, k + 1));
            }
        }

        return res;
    }
public:
    int removeBoxes(vector<int>& boxes) {
        int n=boxes.size();

        return helper(boxes, 0, n-1, 0);
    }
};

/**************************************** Top-Down *********************************/
class Solution2 {
    // (2) Top-Down
    void printMatrix(vector<vector<vector<int>>>& dp)
    {
        int rows = dp.size();
        int cols = dp[0].size();
        int ksize = dp[0][0].size();
        
        //cout << "  ";
        for(int i = 0; i < cols; i++)
        {
            cout.width(3*ksize);
            cout << "(" << i << ")";
        }
        cout << endl;
        for(int r = 0; r < rows; r++)
        {
            cout << r << ")";
            for(int c = 0; c < cols; c++)
            {
                cout << "{";
                for(int k = 0; k < ksize; k++)
                {
                    cout.width(2);
                    cout << dp[r][c][k] << " ";
                }
                cout << "}";
            }
            cout << endl;
        }
    }

    int helper(vector<int>& boxes, int i, int j, int k, vector<vector<vector<int>>>& dp) 
    {
        if (i > j) return 0;
        if (dp[i][j][k] != -1) return dp[i][j][k];

        // all boxes of the same color continuously should be grouped together
        for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++); 
        
        int res = (k + 1) * (k + 1) + helper(boxes, i + 1, j, 0, dp);

        for (int m = i + 1; m <= j; m++) 
        {
            if (boxes[i] == boxes[m]) 
            {
                res = max(res, helper(boxes, i + 1, m - 1, 0, dp) + helper(boxes, m, j, k + 1, dp));
            }
        }

        dp[i][j][k] = res;
        return res;
    }
public:
    int removeBoxes(vector<int>& boxes) {
        int n=boxes.size();
        vector<vector<vector<int>>> memo(n, vector<vector<int>>(n, vector<int>(n, -1)));

        int status = helper(boxes, 0, n-1, 0, memo);
        //printMatrix(dp);
        return status;
    }
};

/****************************** Bottom-Up *******************************/
class Solution {
    // (3) Bottom-up
    void printMatrix(vector<vector<vector<int>>>& dp)
    {
        int rows = dp.size();
        int cols = dp[0].size();
        int ksize = dp[0][0].size();
        
        for(int i = 0; i < cols; i++)
        {
            cout.width(3*ksize);
            cout << "(" << i << ")";
        }
        cout << endl;
        for(int r = 0; r < rows; r++)
        {
            cout << r << ")";
            for(int c = 0; c < cols; c++)
            {
                cout << "{";
                for(int k = 0; k < ksize; k++)
                {
                    cout.width(2);
                    cout << dp[r][c][k] << " ";
                }
                cout << "}";
            }
            cout << endl;
        }
    }
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(n, 0)));
        
        for(int i = 0; i < n; i++)
        {
            for(int k = 0; k <= i; k++)
                dp[i][i][k] = (k+1)*(k+1);
        }
        
        for(int l = 1; l < n; l++)
        {
            for(int j = l; j < n; j++)
            {
                int i = j - l;
                for(int k = 0; k <= i; k++)
                {
                    int res = (k+1)*(k+1) + dp[i+1][j][0];
                    for(int m = i+1; m <= j; m++)
                    {
                        if (boxes[m] == boxes[i])
                        {
                            res = max(res, dp[i+1][m-1][0] + dp[m][j][k+1]);
                        }
                    }
                    dp[i][j][k] = res;
                }
            }
        }
        
        //printMatrix(dp);
        return ((n == 0)? 0 : dp[0][n-1][0]);
    }
};