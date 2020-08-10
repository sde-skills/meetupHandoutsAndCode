public class RodCuttingMaximize {


    public static int count = 0;

    public static int rodCuttingMaximize(int[] wt, int[] val, int len, int n) {

        count++;

        if(len == 0 || n == 0) {
            return 0;
        }

        if(wt[n-1] <= len) {

            return Math.max(
                    val[n-1] + rodCuttingMaximize(wt, val, len - wt[n-1], n),
                    rodCuttingMaximize(wt, val, len, n-1));
        } else {
            return rodCuttingMaximize(wt, val, len , n -1);
        }

    }

    public static void main(String[] args) {

        int[] rd =  {2,  3,   4,  5,   2,  1};
        int[] val = {130, 10, 20,  20,  10,  25};
        int len = 8;
        int n = rd.length;

        System.out.println(rodCuttingMaximize(rd, val, len, n));

        System.out.println(count); // COMPLEXITY -> (2 ^ N ) * N
    }
}