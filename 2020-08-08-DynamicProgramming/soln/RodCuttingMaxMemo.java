public class RodCuttingMaxMemo {


    public static int count = 0;

    public static int rodCuttingMaxMemo(int[] rdLen, int[] val, int len, int n, int[][] arr) {

        count++;

        if(len == 0 || n == 0) {
            return 0;
        }

        if(arr[n][len] != -1) {
            return arr[n][len];
        }

        if(rdLen[n-1] <= len) {
            return arr[n][len] = Math.max(
                    val[n-1] + rodCuttingMaxMemo(rdLen, val, len - rdLen[n-1], n, arr),
                    rodCuttingMaxMemo(rdLen, val, len, n-1, arr));
        } else {
            return arr[n][len] =  rodCuttingMaxMemo(rdLen, val, len , n -1, arr);
        }

    }

    public static void main(String[] args) {

        int[] rdLen =  {2,  3,   4,  5,   2,  1};
        int[] val = {130, 10, 20,  20,  10,  25};
        int len = 8;
        int n = rdLen.length;

        int[][] arr = new int[n+1][len+1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= len; j++) {
                arr[i][j] = -1;
            }
        }

        System.out.println(rodCuttingMaxMemo(rdLen, val, len, n, arr));

        System.out.println(count); // COMPLEXITY -> (2 ^ N ) * N
    }
}
