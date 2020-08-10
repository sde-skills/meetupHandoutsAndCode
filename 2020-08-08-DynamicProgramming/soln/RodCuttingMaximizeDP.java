public class RodCuttingMaximizeDP {


    public static int count = 0;

    public static int rodCuttingMaximizeDP(int[] rdLen, int[] val, int len, int n, int[][] arr) {

        count++;

        // Initialize
        for(int i = 0; i <= n; i++) {
            arr[i][0] = 0;
        }

        for(int j = 0; j <= len; j++) {
            arr[0][j] = 0;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= len; j++) {

                if(rdLen[i-1] <= j) {
                    arr[i][j] = Math.max(val[i-1] + arr[i][j - rdLen[i-1]],
                                            arr[i-1][j]);
                } else {
                    arr[i][j] = arr[i-1][j];
                }
            }
        }

       return arr[n][len];
    }

    public static void main(String[] args) {

        int[] rd =  {2,  3,   4,  5,   2,  1};
        int[] val = {130, 10, 20,  20,  10,  25};
        int len = 8;
        int n = rd.length;

        int[][] arr = new int[n+1][len+1];

        System.out.println(rodCuttingMaximizeDP(rd, val, len, n ,arr));

        System.out.println(count); // COMPLEXITY -> (2 ^ N ) * N
    }
}
