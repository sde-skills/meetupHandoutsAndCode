class Solution {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while(right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right],0) + 1);

           
            while(map.lastKey() - map.firstKey() > limit) {
                map.replace(nums[left], map.get(nums[left])-1);
                if(map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
          
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}