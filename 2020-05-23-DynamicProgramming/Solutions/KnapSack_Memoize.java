public class KnapSack_Memoize {

    public static int count = 0; // complexity

    public static int knapsackMemoize(int[] wt, int[] val, int[][] arr, int w, int n ) {

        count++;

        if(w == 0 || n == 0) {
            return 0;
        }

        if(arr[w][n] != -1) {
            return arr[w][n];
        }

        if(wt[n-1] <= w) {
            return arr[w][n] = Math.max(val[n-1] + knapsackMemoize(wt, val, arr, w - wt[n-1], n-1) ,
                    knapsackMemoize(wt, val, arr, w, n-1));
        } else {
            return arr[w][n] = knapsackMemoize(wt, val, arr, w, n-1);
        }

    }


    public static void main(String[] args) {

        int[] wt  = {2, 6, 1, 9 , 4, 3, 5} ;
        int[] val = {20,30,10,80,65,25,90};
        int w = 7;
        int n = wt.length;

        int[][] arr = new int[n+1][w+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= w; j++) {
                arr[i][j] = -1;
            }
        }

        int res = knapsackMemoize(wt, val, arr, w, n);

        System.out.println(arr[n][w]);
        System.out.println(res);
        System.out.println(count); //  M * N

    }
}