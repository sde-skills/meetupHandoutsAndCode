
/* Class name is maitained to match the leetcode solution, This file has three solutions. 
First one will get TimeLimit Exceeded that is expected. */

// Approach1: Very simple with high time complexity

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                
                long currentSum = 0;
                for(int k = i; k <= j; k++){
                    currentSum += nums[k];
                }
                
                if(currentSum >= lower && currentSum <= upper) {
                    result++;
                }
            }
        }
        
        return result;
    }
}


// Approach2: Avoid calculating sum again and again by creating the sum for array first
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        long[] sumArray = new long[nums.length + 1];
        sumArray[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            sumArray[i+1] = sumArray[i] + nums[i];
        }
        
        
        for (int i = 0; i < nums.length; i++) {
            long currentSum = 0;
            for(int j = i + 1; j < sumArray.length; j++) {
                currentSum = sumArray[j] - sumArray[i];
                if(currentSum >= lower && currentSum <= upper) {
                    result++;
                }
            }
        }
        return result;
    }
}






// Final Efficient solution with Merge Sort way.
public class Solution {
   public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        
        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                + countWhileMergeSort(sums, mid, end, lower, upper);
    
        
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            count += j - k;

            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
        }
        
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}