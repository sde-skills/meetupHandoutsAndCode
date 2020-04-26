class Solution {
    public int shipWithinDays(int[] weights, int D) {
        
        int totalSum = 0;
        int maxVal = Integer.MIN_VALUE;
        for(int w : weights) {            
            maxVal = Math.max(maxVal, w);
            totalSum += w;
        }
        int low = maxVal; //4
        int high = totalSum; //16

        while(low < high){
            int mid = (low + high) /2;
            
            int count = findDaysCount(weights, mid);
            
            if(count > D){ 
                low = mid + 1;
            }else{
                //bias towards left
                high = mid; 
            }
        }
     
        return low;
    }
    
    public int findDaysCount(int[] weights, int target) {
        int days = 1;
        int currSum = 0;
      
        for(int w : weights) {
            
            if(currSum + w > target) {
                days++;
                currSum = 0;
            }
            currSum += w;
        }                                        
        return days;
    }
}

/*
 [1,2,3,4,5,6,7,8,9,10]

Test 1
D - Inf

1. one package per day
2. cap - 10

Test 2
D- 1

Cap - Total sum - 55

Test 3
D- x

------------------------
D - 4
[3,2,2,4,1,4]

ow 4
high 16
mid 10
count 2

low 4
high 10
mid 7
count 3

low 4
high 7
mid 5
count 4

low 6
high 7
mid 6
count 3


*/