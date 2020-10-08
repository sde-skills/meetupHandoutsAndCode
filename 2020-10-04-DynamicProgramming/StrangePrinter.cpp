/**************************** Recursion *********************************************/
class Solution0 {
    // (0) Brute Force 
    int helper(string str)
    {
        int n = str.size();
        if (n <= 0) return 0;
        int ans = INT_MAX;
        
        for(int i = 0, j = i+1; i < n; i++)
        {
            while(j < n && str[i] == str[j]) j++;
            
            string remainStr = str.substr(0, i) + str.substr(j);

            ans = min(ans, helper(remainStr) + 1);
        }
        return ans;
    }
public:
    int strangePrinter(string s) {
        int n = s.size();
        
        return helper(s);
    }
};
class Solution1 {
    // (1) Brute force with k
    int helper(string str, int i, int j, int k) 
    { 
        if (i > j) return 0;

        for (; i + 1 <= j && str[i + 1] == str[i]; i++, k++); 
        int res = 1 + helper(str, i + 1, j, 0);
        
        for (int m = i + 1; m <= j; m++) 
        {
            if (str[i] == str[m]) 
            {
                res = min(res, helper(str, i + 1, m - 1, 0) + helper(str, m, j, k + 1));
            }
        }

        return res;
    }
public:
    int strangePrinter(string s) {
        int n = s.size();
        
        return helper(s, 0, n-1, 0);
    }
};

class Solution2 {
    // (2) Brute force without k
    int helper(string str, int i, int j) 
    {
        if (i > j) return 0;

        int res = 1 + helper(str, i + 1, j);

        for (int m = i + 1; m <= j; m++) 
        {
            if (str[i] == str[m]) 
            {
                res = min(res, helper(str, i + 1, m - 1) + helper(str, m, j));
            }
        }

        return res;
    }
public:
    int strangePrinter(string s) {
        int n = s.size();
        if (n <= 0) return 0;
        int len = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] != s[len]) {
                s[++len] = s[i];
            }
        }
        ++len;
        s = s.substr(0, len);

        n = len;        
        return helper(s, 0, n-1);
    }
};

/*************************** Top-Down ****************************************/
class Solution3 {
    // (3) Top-down
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
    int helper(string str, int i, int j, vector<vector<int>>& dp) 
    {
        if (i > j) return 0;
        if (i == j) return 1;
        if (dp[i][j] > -1) return dp[i][j];

        int res = 1 + helper(str, i + 1, j, dp);

        for (int m = i + 1; m <= j; m++) 
        {
            if (str[i] == str[m]) 
            {
                res = min(res, helper(str, i + 1, m - 1, dp) + helper(str, m, j, dp));
            }
        }

        dp[i][j] = res;
        return res;
    }
public:
    int strangePrinter(string s) {
        int n = s.size();
        if (n <= 0) return 0;
        
        int len = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] != s[len]) {
                s[++len] = s[i];
            }
        }
        ++len;
        s = s.substr(0, len);
        
        vector<vector<int>> memo(len, vector<int>(len, -1));
        int status = helper(s, 0, len-1, memo);
        //printMatrix(memo);
        return status;
    }
};

/**************************** Bottom-Up *************************************/
class Solution4 {
    // (4) Bottom-up 
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
    int strangePrinter(string s) {
        int n = s.size();
        if (n <= 0) return 0;

        int len = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] != s[len]) {
                s[++len] = s[i];
            }
        }
        ++len;
        s = s.substr(0, len);

        n = len;        
        vector<vector<int>> dp(n, vector<int>(n, 0));

        for(int i = 0; i < n; i++)
        {
            dp[i][i] = 1;
        }

        for(int l = 1; l < n; l++)
        {
            for(int j = l; j < n; j++)
            {
                int i = j - l;
                int res = 1 + dp[i+1][j];
                
                for(int m = i+1; m <= j; m++)
                {
                    if (s[m] == s[i])
                    {
                        res = min(res, dp[i+1][m-1] + dp[m][j]);
                    }
                }
                dp[i][j] = res;
            }
        }
        
        printMatrix(dp);
        
        return ((n == 0)? 0 : dp[0][n-1]);
    }
};