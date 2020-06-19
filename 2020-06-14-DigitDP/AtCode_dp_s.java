//Ref : https://atcoder.jp/contests/dp/tasks/dp_s
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author roger
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        SDigitSum solver = new SDigitSum();
        solver.solve(1, in, out);
        out.close();
    }

    static class SDigitSum {
        int[][][] dp = new int[10002][102][2];
        long MOD = 1000000007;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String str = in.nextString();
            int d = in.nextInt();
            resetDPArray();
            long res = sum(str, 0, 0, 1, d);
            //remove 0
            out.println((res - 1 + MOD) % MOD);
        }

        private long sum(String str, int pos, int remainder, int restricted, int d) {
            if (pos == str.length()) {
                return remainder == 0 ? 1 : 0;
            }
            int res = 0;
            if (dp[pos][remainder][restricted] != -1) {
                return dp[pos][remainder][restricted];
            }
            int limit = restricted == 1 ? str.charAt(pos) - '0' : 9;
            for (int i = 0; i <= limit; i++) {
                int newRestricted = restricted;
                if (i < limit) {
                    newRestricted = 0;
                }
                res = (int) ((res + sum(str, pos + 1, (remainder + i) % d, newRestricted, d)) % MOD);
            }
            return dp[pos][remainder][restricted] = res;
        }

        private void resetDPArray() {
            for (int[][] x : dp) {
                for (int[] y : x) {
                    Arrays.fill(y, -1);
                }
            }
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void close() {
            writer.close();
        }

        public void println(long i) {
            writer.println(i);
        }

    }
}

