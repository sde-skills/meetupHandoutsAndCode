public class KnapSack {


    public static int count = 0;

    public static int knapSackMaximize(int[] wt, int[] val, int w, int n) {

        count++;

        if(w == 0 || n == 0) {
            return 0;
        }

        if(wt[n-1] <= w) {

            return Math.max(val[n-1] + knapSackMaximize(wt, val, w - wt[n-1], n-1) ,
                    knapSackMaximize(wt, val, w, n-1));
        } else {

            return knapSackMaximize(wt, val, w , n -1);
        }

    }

    public static void main(String[] args) {

        int[] wt =  {10,  5,   80,  25,  70,  20};
        int[] val = {100, 110, 120, 220, 330, 90};
        int w = 90;
        int n = wt.length;

        System.out.println(knapSackMaximize(wt, val, w, n));

        System.out.println(count); // COMPLEXITY -> 2 ^ N
    }
}