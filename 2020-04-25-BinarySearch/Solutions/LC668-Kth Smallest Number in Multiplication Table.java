class Solution {
    public int findKthNumber(int m, int n, int k) {
    
        int low = 1, high = m * n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countSmallerNumbers(m, n, mid);
            if (count < k) {
                low = mid + 1;                
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int countSmallerNumbers(int m, int n, int mid) {
        int count = 0;
        
        //i = 3 - 3 6 9
        //mid = 8
        //count = 2 
        for (int i = 1; i <= m; i++) {
            count += Math.min(mid / i, n);
        }
        return count;
    }
        
    
}