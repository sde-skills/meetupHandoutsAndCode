//Ref : https://codeforces.com/contest/628/problem/D
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
        DMagicNumbers solver = new DMagicNumbers();
        solver.solve(1, in, out);
        out.close();
    }

    static class DMagicNumbers {
        int m;
        int d;
        int MOD = (int) 1e9 + 7;
        int MAX = 2002;
        String str;
        int[][][][][] dp;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            m = in.nextInt();
            d = in.nextInt();
            str = in.nextString();

            int add = 0;
            if (valid(str)) {
                add++;
            }

            dp = new int[2][2][2][MAX][MAX];
            restDPArray();

            int first = calculate(1, 0, 0, 0, 0);

            str = in.nextString();
            restDPArray();
            int second = calculate(1, 0, 0, 0, 0);

            int res = ((second - first) + MOD) % MOD;
            res = add(res, add);
            out.println(res);
        }

        private int calculate(int restricted, int start, int parity, int pos, int mod) {
            if (pos == str.length()) {
                return mod == 0 ? 1 : 0;
            }
            if (dp[restricted][start][parity][pos][mod] != -1) {
                return dp[restricted][start][parity][pos][mod];
            }

            int res = 0;
            int limit = restricted == 1 ? str.charAt(pos) - '0' : 9;

            if (parity == 1) {
                //No need to proceed if limit is less than the digit 'd'
                if (d > limit) {
                    dp[restricted][start][parity][pos][mod] = res;
                } else {
                    int newRestricted = restricted;
                    if (d < limit) {
                        newRestricted = 0;
                    }
                    res = calculate(newRestricted, start, parity ^ 1, pos + 1, (mod * 10 + d) % m);
                    dp[restricted][start][parity][pos][mod] = res;
                }
            } else {
                for (int i = 0; i <= limit; i++) {
                    //it is not allowed by definition when parity = 0
                    if (i == d)
                        continue;
                    int newRestricted = restricted;
                    if (i < limit) {
                        newRestricted = 0;
                    }
                    if (start == 0 && i == 0) {
                        res = add(res, calculate(newRestricted, 0, 0, pos + 1, mod));
                    } else {
                        res = add(res, calculate(newRestricted, 1, parity ^ 1, pos + 1, (mod * 10 + i) % m));
                    }
                }
            }

            return dp[restricted][start][parity][pos][mod] = res;
        }

        private void restDPArray() {
            for (int[][][][] w : dp) {
                for (int[][][] x : w) {
                    for (int[][] y : x) {
                        for (int[] z : y) {
                            Arrays.fill(z, -1);
                        }
                    }
                }
            }
        }

        private int add(int a, int b) {
            a %= MOD;
            b %= MOD;
            a += b;
            a %= MOD;
            return a;
        }

        private boolean valid(String str) {
            int parity = 0;
            int mod = 0;
            for (int i = 0; i < str.length(); i++) {
                if (parity == 1) {
                    if (str.charAt(i) - '0' != d) {
                        return false;
                    }
                } else if (str.charAt(i) - '0' == d) {
                    return false;
                }
                mod = (mod * 10 + (str.charAt(i) - '0')) % m;
                parity ^= 1;
            }
            return mod == 0;
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

