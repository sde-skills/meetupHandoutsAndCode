class Solution {
    
   private int searchHelper(int[] nums, int target, boolean higher){
       
       if(nums == null || nums.length == 0) return -1;
       
       int low = 0, high = nums.length-1, result = -1;
       
       while(low <= high){
           int mid = low + (high-low) / 2;
           
           if(nums[mid] == target){
               result = mid;
               if(higher)
                   low = mid + 1;
               else 
                   high = mid - 1;
               
           }
           else if(nums[mid] < target) low = mid + 1;
           else high = mid - 1;
           
           
       }
       
       return result;
   }
   public int[] searchRange(int[] nums, int target) {
       
       int[] result = new int[]{-1,-1};
       result[0] = searchHelper(nums, target, false);
       if(result[0] == -1) return result;
       result[1] = searchHelper(nums, target, true);
       return result;
       
    }
}