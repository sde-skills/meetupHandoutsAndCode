class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        
        if not nums:
            return []
        
        
        left,right = [1]*len(nums), [1]*len(nums)
        
        for i in range(1,len(nums)):
            left[i] = left[i-1] * nums[i-1]
            
        for i in range(len(nums)-2, -1, -1):
            right[i] = right[i+1] * nums[i+1]
        
       
    
        output = []
        
        for l,r in zip(left, right):
            output.append(l*r)
            
        return output
