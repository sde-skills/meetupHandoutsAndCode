public class NoOfWaysCoinChange {

    public static int count = 0;

    public static int coinChangeNoOfWays(int[] coins, int sum, int n) {

        count++;

        if(n == 0) {
            return 0;
        }

        if(sum == 0) {
            return 1;
        }

        if(coins[n-1] > sum) {
            return coinChangeNoOfWays(coins, sum, n-1);
        } else {
            return coinChangeNoOfWays(coins, sum - coins[n-1], n) +
                    coinChangeNoOfWays(coins, sum, n-1);
        }

    }

    public static void main(String[] args) {

        int[] coins =  {1,  2,   5};
        int sum = 5;
        int n = coins.length;

        System.out.println(coinChangeNoOfWays(coins, sum, n));

        System.out.println(count); // COMPLEXITY -> (2 ^ N ) * N
    }
}
