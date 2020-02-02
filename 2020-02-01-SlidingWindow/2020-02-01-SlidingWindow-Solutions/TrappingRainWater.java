class Solution {
    /* This solution stores the maximum elevation on left for each index.
    ** Similarly, it stores the maxinum elevation on right for each index.
    ** For any index, max wather which can be trapped would be determined by
    ** min of left maximun elevation and right maximun elevation.
    */
    public int trap11(int[] height) {
        int units = 0;
        if(height==null || height.length < 3)
            return units;
        
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        //Store the maximum elevation on left for each index
        for(int i=1; i<leftMax.length; i++)
            leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
        
        //Store the maximum elevation on right for each index
        for(int i=rightMax.length-2; i>=0; i--)
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        
        //Determine the unit of water that can be trapped at each index.
        for(int i=0; i<height.length; i++){
            int diff = Math.min(leftMax[i], rightMax[i]) - height[i];
            units += diff > 0? diff : 0;
        }
        return units;
    }
    
    /* Imagine a container with its edges at leftMaxInd and rightMaxInd
    ** trapped water can be determined by edge with smaller height.
    ** This solution uses sliding windows to move the edges of the imaginary container.
    */
    public int trap(int[] height) {
        int units = 0;
        if(height == null)
            return units;
        
        int leftInd = 0, leftMaxInd = 0;
        int rightInd = height.length-1, rightMaxInd = rightInd;
        while(leftMaxInd < rightMaxInd){
            //If right edge of the container is longer in height, move smaller edge i.e. left edge.
            if(height[rightMaxInd] > height[leftMaxInd]){
                if(height[leftMaxInd] > height[++leftInd])
                    units += height[leftMaxInd] - height[leftInd];
                else
                    leftMaxInd = leftInd;
            }
            //If left edge of the container is longer/equal in height, move smaller edge i.e. right edge.
            else{
                if(height[rightMaxInd] > height[--rightInd])
                    units += height[rightMaxInd] - height[rightInd];
                else
                    rightMaxInd = rightInd;
            }
        }
        return units;
    }
}
