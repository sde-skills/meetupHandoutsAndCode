public class NoOfWaysCoinChangeDP {


    public static int count = 0;

    public static int totalWaysCoinChangeDP(int[] coins, int sum, int n, int[][] arr) {

        count++;

        // Initialize
        for(int j = 0; j <= sum; j++) {
            arr[0][j] = 1;
        }

        for(int i = 0; i <= n; i++) {
            arr[i][0] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= sum; j++) {

                if(coins[i-1] <= j) {
                    arr[i][j] = arr[i][j - coins[i-1]] +
                                            arr[i-1][j];
                } else {
                    arr[i][j] = arr[i-1][j];
                }
            }
        }

       return arr[n][sum];
    }

    public static void main(String[] args) {

        int[] coins =  {1, 2,  5};
        int sum = 5;
        int n = coins.length;

        int[][] arr = new int[n+1][sum+1]; // by default initialization is 0 for matrix.

        System.out.println(totalWaysCoinChangeDP(coins, sum, n ,arr));

        System.out.println(count); // COMPLEXITY ->
    }
}
