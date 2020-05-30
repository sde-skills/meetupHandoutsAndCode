class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
    
        int low = matrix[0][0];    
        int high = matrix[n - 1][n - 1];

        while(low < high) { 
            int mid = (low + (high - low) / 2);

            int count = findEleCntLessEqToMid(matrix, n, mid);
            //System.out.println(count);
            if (count < k) {
                low = mid + 1;   
            } else {
                high = mid;
            }
        }

        return low;
    }
    
    int findEleCntLessEqToMid(int[][] matrix, int n, int mid) {
        int count = 0;
        for(int i = 0; i < n; i++)  {
            int j = 0;
            while (j < n && matrix[i][j] <= mid) {
                j++;
                count++;
            }
        }
        return count;
    }
}