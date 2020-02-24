class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        difference_map = {}

        for i,num in enumerate(nums):

            if num in difference_map:
             return [difference_map[num], i]

            difference_map[target - num] = i
    
