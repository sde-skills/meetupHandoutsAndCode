/*
        0  1  2  3  4  5
Input: [2, 1, 5, 1, 3, 2], k=3
           |_____|
*/

int maxSumSubarray(vector<int>& nums, int k)
{
    int start = 0;
    int maxSum = INT_MIN;
    int localSum = 0;

    for(int end=0 ; end < nums.size(); end++)
    {
        localSum += nums[end];

        if(end - start == k -1)
        {
            maxSum = max(maxSum, localSum);
            localSum -= nums[start];
            start++;
        }
    }

    return max(maxSum, localSum);
}

/*
Given an array of positive numbers and a positive number ‘k’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘k’. Return 0, if no such subarray exists.

Input: [2, 1, 5, 2, 3, 2] k=7.
                |___|
Output: 2
Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].

*/
int smallestSubarray(vector<int>& nums, int k)
{
    int minSubArraySize = INT_MAX;
    int start = 0;
    int localSum = 0;

    for(int end = 0; end < nums.size(); end++)
    {
        localSum+= nums[end];

        while(localSum >= k)
        {
            minSubArraySize = min(minSubArraySize, end - start + 1);
            localSum-= nums[start];
            start++;
        }
    }

    return minSubArraySize;
}

/*
Given a string, find the length of the longest substring in it with no more than K distinct characters.

Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".


*/
int lengthOfLongestSubstringKDistinct(string s, int k) {
    map<char, int> auxMap;
    int maxSize = 0;
    int startWindow = 0;
    int localSize = 0;

    for(int endWindow = 0; endWindow < s.size(); endWindow++)
    {
        auxMap[s[endWindow]]++;
        localSize++;

        while(auxMap.size() > k)
        {
            maxSize = max(maxSize, localSize-1);
            if(auxMap[s[startWindow]] == 1)
                auxMap.erase(s[startWindow]);
            else
                auxMap[s[startWindow]]--;
            startWindow++;
            localSize--;
        }
    }

    return max(maxSize,localSize);
}
