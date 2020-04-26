class Solution {
   public int[] searchRange(int[] nums, int target) {
       
       if(nums == null || nums.length == 0)
           return new int[] {-1, -1};
       
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int mid = (low + high) /2;
            if(target > nums[mid]){
                low = mid + 1;
            }else{
                //update high to move closer to start
                high = mid;
            }
        }
        if(nums[low] == target)
            return low;
        return -1;
    }

    private int findLast(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            
            //to make bias towards to right
            int mid = (low + high) / 2 + 1;
            if(target < nums[mid]){
                high = mid - 1;
            }else{
                //update low to move closer to end
                low = mid;
            }
        }   
        if(nums[low] == target)
            return low;
        return -1;
    }
}