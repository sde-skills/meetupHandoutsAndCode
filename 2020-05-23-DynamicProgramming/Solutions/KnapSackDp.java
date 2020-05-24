public class KnapSackDp {


    public static void knapsackDP(int[] wt, int[] val, int[][] arr, int w, int n) {

        // Initialize
        for(int i = 0; i <= n; i++) {
            arr[i][0] = 0;
        }

        for(int j = 0; j <= w; j++) {
            arr[0][j] = 0;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= w ; j++) {

                if(wt[i-1] <= j ) {
                    arr[i][j] = Math.max(val[i-1] + arr[i-1][j - wt[i - 1]]
                            , arr[i-1][j]);
                } else {
                       arr[i][j] = arr[i-1][j];
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] wt  = {2, 6, 1, 9 , 4, 3, 5} ;
        int[] val = {20,30,10,80,65,25,90};
        int w = 7;
        int n = wt.length;

        int[][] arr = new int[n+1][w+1];

        knapsackDP(wt, val, arr, w, n);

        System.out.println(arr[n][w]);
       // System.out.println(res);
    }
}