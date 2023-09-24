Question 1: Valid Triangle Number
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Solution1: Binary Search:
``` python
class Solution(object):
    def triangleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        numslength = len(nums)
        if numslength < 3: return 0
        totalCount = 0

        def binarySearch(left, right, pivotNum):

            while left <= right:
                mid = left + (right - left)//2
                if newNums[mid] >= pivotNum:
                    right = mid - 1
                else:
                    left = mid + 1
            return left

        newNums = sorted(nums)
        for leftIdx in range(numslength-2):
            if newNums[leftIdx] == 0: continue
            rightIdx = leftIdx + 2
            for midIdx in range(leftIdx+1, numslength-1):
                # we are updating the right index
                rightIdx = binarySearch(rightIdx, numslength-1, newNums[leftIdx] + newNums[midIdx]) 
                totalCount += rightIdx - midIdx - 1

        return totalCount
```
Solution2: Three Pointers
``` python
class Solution:
    def triangleNumber(self, nums: List[int]) -> int:

        numslength = len(nums)
        if numslength < 3:
            return 0
        newNums = sorted(nums)
        totalNum = 0

        for rightIdx in range(numslength-1, 1, -1):
            if newNums[rightIdx] == 0: continue
            midIdx = rightIdx -1
            leftIdx = 0
            while leftIdx < midIdx:
                if newNums[leftIdx] + newNums[midIdx] <= newNums[rightIdx]:
                    leftIdx += 1
                else:
                    totalNum += midIdx - leftIdx
                    midIdx -= 1
        return totalNum
```

Question 2: Shortest Unsorted Continuous Subarray
Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.

Return the shortest such subarray and output its length.

Solution: Partitioning
``` python
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        
        numsLen = len(nums)
        if numsLen < 2: return 0
        
        leftIdx, rightIdx = 0, numsLen-1
        
        while leftIdx < numsLen-1 and nums[leftIdx] <= nums[leftIdx+1]:
            leftIdx += 1
        if leftIdx == numsLen-1:
            return 0
        while rightIdx > leftIdx and nums[rightIdx] >= nums[rightIdx-1]:
            rightIdx -= 1
            
        minNum, maxNum = float("inf"), float("-inf")
        for idx in range(leftIdx, rightIdx+1):
            minNum = min(minNum, nums[idx])
            maxNum = max(maxNum, nums[idx])

        while leftIdx >= 0 and nums[leftIdx] > minNum:
            leftIdx -= 1
            
        while rightIdx < numsLen and nums[rightIdx] < maxNum:
            rightIdx += 1
            
        return rightIdx - leftIdx - 1
```

Question 3: Count of Smaller Numbers After Self
Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

Solution: Using Merge Sort
``` python
class Solution:
    def countSmaller(self, nums: List[int]) -> List[int]:
        
        if not nums:
            return []
        numsLen = len(nums)
        result = [0] * numsLen
        arr = [[i,v] for i, v in enumerate(nums)]
        self.mergeSort(arr, 0, len(nums)-1, result)
        return result
    
    def mergeSort(self, inputNums, start, end, res):
        if start >= end:
            return
        mid = (start + end) >> 1

        self.mergeSort(inputNums, start, mid, res)
        self.mergeSort(inputNums, mid+1, end, res)
        self.merge_arr(inputNums, start, mid, end, res)
        
    def merge_arr(self, inputList, left, mid, right, res):
        leftIdx, rightIdx = left, mid+1
        smallerThan = 0
        temp = []
        while leftIdx <= mid and rightIdx <= right:
            if inputList[leftIdx][1] <= inputList[rightIdx][1]:
                temp.append(inputList[leftIdx])
                res[inputList[leftIdx][0]] += smallerThan
                leftIdx += 1
            else:
                temp.append(inputList[rightIdx])
                smallerThan += 1
                rightIdx += 1
        while leftIdx <= mid:
            temp.append(inputList[leftIdx])
            res[inputList[leftIdx][0]] += smallerThan
            leftIdx += 1
        while rightIdx <= right:
            temp.append(inputList[rightIdx])
            rightIdx += 1
        inputList[left:right+1] = temp # Same thing as below commented lines (Copying to initial array)

        # curIdx = left
        # for arr in temp:
        #     inputList[curIdx] = arr
        #     curIdx += 1
```

Question 4: Count of Range Sum
Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.

Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.

Solution: Using Merge Sort
``` python
class Solution:
    def countRangeSum(self, nums: List[int], lower: int, upper: int) -> int:

        if not nums: return 0
        numslength = len(nums)
        prefixSum = [0 for _ in range(numslength+1)]
        prefixLength = len(prefixSum)

        for idx, number in enumerate(nums):
            prefixSum[idx+1] = prefixSum[idx] + nums[idx]

        cache = [0 for _ in range(prefixLength)]
        res = [0]
        def mergeSortWhileCounting(start, end):
            if start >= end: return 0

            mid = start + (end - start) // 2
            count = mergeSortWhileCounting(start, mid) + mergeSortWhileCounting(mid+1, end)

            curIdx = start
            rWind = rIdx = kIdx = mid+1

            for lWind in range(start, mid+1):
                while rWind <= end and prefixSum[rWind] - prefixSum[lWind] <= upper: rWind += 1
                while rIdx <= end and prefixSum[rIdx] - prefixSum[lWind] < lower: rIdx += 1
                while kIdx <= end and prefixSum[kIdx] < prefixSum[lWind]:
                    cache[curIdx] = prefixSum[kIdx]
                    curIdx += 1
                    kIdx += 1

                cache[curIdx] = prefixSum[lWind]
                curIdx += 1
                count += rWind - rIdx

            while kIdx <= end:
                cache[curIdx] = prefixSum[kIdx]
                kIdx, curIdx = kIdx+1, curIdx+1

            for idx in range(start, end+1):
                prefixSum[idx] = cache[idx]
                
            return count

        return mergeSortWhileCounting(0, prefixLength-1)
```