class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0], max = nums[0], result = max;
        
        for (int i = 1; i < nums.length; i++) {
            
            if (nums[i] < 0) {
                int tmp = min;
                min = max;
                max = tmp;
            }
            
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            
            result = Math.max(result, max);
        }
        
        return result;
    }
}