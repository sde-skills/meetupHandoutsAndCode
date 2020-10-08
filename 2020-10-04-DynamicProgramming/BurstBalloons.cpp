/******************************** Recursion *************************/
class Solution0 {
    // (0) Brute Force - basic
    int helper(vector<int>& nums)
    {
        int n = nums.size();
        if (n <= 0) return 0;

        int ans = 0;
        for(int i = 0; i < n; i++)
        {
            vector<int> remainNums;
            int left = (i > 0) ? nums[i-1] : 1;
            int right = (i < (n-1)) ? nums[i+1] : 1;
        
            remainNums = nums;
            remainNums.erase(remainNums.begin()+i);

            ans = max(ans, helper(remainNums) + (left*right*nums[i]));
        }
        return ans;
    }
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();
        
        return helper(nums);
    }
};

class Solution1 {
    // (1) Brute force
    int helper(vector<int> nums, int left, int right) 
    {
        if (left + 1 == right) return 0;

        int res = 0;
        
        for (int m = left + 1; m < right; m++)
        {
            res = max(res, (nums[left]*nums[m]*nums[right]) + helper(nums, left, m) + helper(nums, m, right));
        }

        return res;
    }
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();

        nums.insert(nums.begin(), 1);
        nums.push_back(1);
        n += 2;

        return helper(nums, 0, n-1);
    }
};

/******************************* Top-Down *************************/
class Solution2 {
    // (2) Top-down
    void printMatrix(vector<vector<int>> dp)
    {
        int rows = dp.size();
        int cols = dp[0].size();
        
        cout << "  ";
        for(int i = 0; i < cols; i++)
        {
            cout.width(3);
            cout << "(" << i << ")";
        }
        cout << endl;
        for(int r = 0; r < rows; r++)
        {
            cout << r << ")";
            for(int c = 0; c < cols; c++)
            {
                cout.width(4);
                cout << dp[r][c] << " ";
            }
            cout << endl;
        }
    }

    int helper(vector<int> nums, int left, int right, vector<vector<int>>& memo) 
    {
        if (left + 1 == right) return 0;
        if (memo[left][right] > -1) return memo[left][right];
        
        int res = 0;
        for (int m = left + 1; m < right; m++) 
        {
            res = max(res, (nums[left] * nums[m]* nums[right]) + helper(nums, left, m, memo) + helper(nums, m, right, memo));
        }

        memo[left][right] = res;
        return res;
    }
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();

        nums.insert(nums.begin(), 1);
        nums.push_back(1);
        n += 2;

        vector<vector<int>> memo(n, vector<int>(n, -1));
        int ans = helper(nums, 0, n-1, memo);
        //printMatrix(memo);
        return ans;
    }
};
/******************************** Bottom-Up ****************************/
class Solution {
    // (3) Bottom-up
    void printMatrix(vector<vector<int>> dp)
    {
        int rows = dp.size();
        int cols = dp[0].size();
        
        cout << "  ";
        for(int i = 0; i < cols; i++)
        {
            cout.width(3);
            cout << "(" << i << ")";
        }
        cout << endl;
        for(int r = 0; r < rows; r++)
        {
            cout << r << ")";
            for(int c = 0; c < cols; c++)
            {
                cout.width(4);
                cout << dp[r][c] << " ";
            }
            cout << endl;
        }
    }
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();

        nums.insert(nums.begin(), 1);
        nums.push_back(1);
        n += 2;
        
        vector<vector<int>> dp(n, vector<int>(n, 0));
        
        for(int l = 2; l < n; l++)
        {
            for(int j = l; j < n; j++)
            {// i is left and j is right
                int i = j - l;
                
                int res = 0;
                for(int m = i+1; m < j; m++)
                {
                    res = max(res, (nums[i]*nums[m]*nums[j]) + dp[i][m] + dp[m][j]);
                }
                dp[i][j] = res;
            }
        }
        printMatrix(dp);
        return dp[0][n-1];
    }
};