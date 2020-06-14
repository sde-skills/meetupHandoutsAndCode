//Ref : https://atcoder.jp/contests/abc135/tasks/abc135_d
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
        DDigitsParade solver = new DDigitsParade();
        solver.solve(1, in, out);
        out.close();
    }

    static class DDigitsParade {
        long[][] dp = new long[100005][13];
        int MOD = (int) (1e9) + 7;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String str = in.nextString();
            resetDPArray();
            out.println(calculate(str, 0, 0));
        }

        private long calculate(String str, int pos, int remainder) {
            if (pos == str.length()) {
                return remainder == 5 ? 1 : 0;
            }
            if (dp[pos][remainder] != -1) {
                return dp[pos][remainder];
            }
            long res = 0;
            char curr = str.charAt(pos);
            if (curr == '?') {
                for (int i = 0; i <= 9; ++i) {
                    res = (res + calculate(str, pos + 1, (remainder * 10 + i) % 13)) % MOD;
                }
            } else {
                res = calculate(str, pos + 1, (remainder * 10 + (curr - '0')) % 13);
            }
            return dp[pos][remainder] = res;
        }

        private void resetDPArray() {
            for (long[] x : dp) {
                Arrays.fill(x, -1);
            }
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
}

