public class NoOfWaysCoinChangeMemo {


    public static int count = 0;

    public static int NoOfWaysCoinChaneMemoization(int[] coins, int sum, int n, int[][] arr) {

        count++;

        if(n == 0) {
            return 0;
        }

        if(sum == 0) {
            return 1;
        }

        if(arr[n][sum] != -1) {
            return arr[n][sum];
        }

        if(coins[n-1] <= sum) {
            return arr[n][sum] = NoOfWaysCoinChaneMemoization(coins, sum - coins[n-1], n, arr) +
                    NoOfWaysCoinChaneMemoization(coins, sum, n-1, arr);
        } else {
            return arr[n][sum] =  NoOfWaysCoinChaneMemoization(coins, sum, n -1, arr);
        }

    }

    public static void main(String[] args) {

        int[] coins =  {1,2,5};
        int sum = 5;
        int n = coins.length;

        int[][] arr = new int[n+1][sum+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                arr[i][j] = -1;
            }
        }

        System.out.println(NoOfWaysCoinChaneMemoization(coins, sum, n, arr));

        System.out.println(count); // COMPLEXITY -> (2 ^ N ) * N
    }
}
