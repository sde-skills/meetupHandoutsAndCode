class Solution {
public:
    int longestOnes(vector<int>& A, int K) {
        if (A.size() == 0)  return 0;
        
        int start = 0;
        int end = 0;
        int maxLength = 0;
        
        while(end < A.size()){
            if (A[end] == 0)
                K--;
            
            while(K < 0){
                if (A[start] == 0)
                    K++;   
                
                start++;
            }
            
            maxLength = max(maxLength, end - start + 1);
            end++;
        }
        
        return maxLength;
    }
};
