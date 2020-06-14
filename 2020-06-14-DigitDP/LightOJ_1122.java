//Ref : http://www.lightoj.com/volume_showproblem.php?problem=1122
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        DigitCount solver = new DigitCount();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class DigitCount {
        long[][] dp = new long[12][12];
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int t, m, n;
            t = in.nextInt();
            for (int test = 1; test <= t; test++) {
                m = in.nextInt();
                n = in.nextInt();
                List<Integer> list = new ArrayList<>(m);
                for (int i = 0; i < m; i++) {
                    list.add(in.nextInt());
                }
                resetDPArray();
                long res = calculate(n, list, 0, 0);
                out.println("Case " + test + ": " + res);
            }
        }
 
        private long calculate(int n, List<Integer> list, int pos, int prev) {
            if (pos == n)
                return 1;
            if (dp[pos][prev] != -1) {
                return dp[pos][prev];
            }
            long res = 0;
            for (int i = 0; i < list.size(); i++) {
                if (prev == 0 || Math.abs(prev - list.get(i)) <= 2) {
                    int newPrev = list.get(i);
                    res += calculate(n, list, pos + 1, newPrev);
                }
            }
            return dp[pos][prev] = res;
        }
 
        private void resetDPArray() {
            for (long[] x : dp) {
                Arrays.fill(x, -1);
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
 
        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }
 
        public void println(Object... objects) {
            print(objects);
            writer.println();
        }
 
        public void close() {
            writer.close();
        }
 
    }
}