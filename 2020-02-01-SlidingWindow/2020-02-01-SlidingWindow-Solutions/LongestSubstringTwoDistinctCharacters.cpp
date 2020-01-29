class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
         int n = s.size();
        // No further processing if string length is less 2
        if (n < 3)
            return n;
        
        
        int maxLength = 0;
        int runningLength;
        int left = 0;
        int right = 0;
        
        unordered_map<char, int> lookup;
        int count = 0;
        
        while(right < n)
        {
            if (lookup.find(s[right]) != lookup.end()){
                lookup[s[right]]++;
            } else {
                lookup.emplace(s[right], 1);
                count++;
            }
            
            while (count > 2){
                lookup[s[left]]--;
                if (lookup[s[left]] == 0){
                    lookup.erase(s[left]);
                    count--;
                }
                
                left++;
            }
            
            runningLength = right - left + 1;
            maxLength = max(runningLength, maxLength);
            
            right++;
            
        }
        
        return maxLength;
    }
};



