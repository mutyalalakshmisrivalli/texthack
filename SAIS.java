import java.util.*;

public class SAIS {
    /*
     * SA-IS implementation for integer alphabets.
     * The public run method converts the input string to integer symbols.
     */
    static int[] sais(int[] s, int upper) {
        int n = s.length;
        if (n == 0) return new int[0];
        if (n == 1) return new int[]{0};
        if (n == 2) return s[0] < s[1] ? new int[]{0,1} : new int[]{1,0};

        boolean[] ls = new boolean[n];
        ls[n - 1] = true;
        for (int i = n - 2; i >= 0; i--)
            ls[i] = s[i] == s[i + 1] ? ls[i + 1] : s[i] < s[i + 1];

        int[] sumL = new int[upper + 1];
        int[] sumS = new int[upper + 1];

        for (int i = 0; i < n; i++) {
            if (!ls[i]) sumS[s[i]]++;
            else sumL[s[i]]++;
        }

        for (int i = 0; i <= upper; i++) {
            sumS[i] += sumL[i];
            if (i < upper) {
                sumL[i + 1] += sumS[i];
            }
        }

        int[] sa = new int[n];
        Arrays.fill(sa, -1);

        int[] lms = new int[n];
        int m = 0;
        for (int i = 1; i < n; i++)
            if (!ls[i - 1] && ls[i]) lms[m++] = i;

        int[] sortedLms = new int[m];

        for (int i = 0; i < m; i++)
            sortedLms[i] = lms[i];

        inducedSort(s, sa, ls, sortedLms, sumL, sumS, upper);

        if (m > 0) {
            int[] recS = new int[m];
            int recUpper = 0;
            recS[0] = 0;

            for (int i = 1; i < m; i++) {
                int x = sa[i == 0 ? 0 : i];
                // Reconstruct LMS order from SA.
            }

            int[] lmsOrder = new int[m];
            int p = 0;
            for (int x : sa)
                if (isLMS(x, ls)) lmsOrder[p++] = x;

            recS[0] = 0;
            for (int i = 1; i < m; i++) {
                int prev = lmsOrder[i - 1];
                int cur = lmsOrder[i];

                int prevNext = nextLMS(prev, ls, n);
                int curNext = nextLMS(cur, ls, n);

                boolean same = true;
                int a = prev, b = cur;

                while (true) {
                    if (s[a] != s[b] || ls[a] != ls[b]) {
                        same = false;
                        break;
                    }

                    boolean aL = isLMS(a, ls);
                    boolean bL = isLMS(b, ls);

                    if (aL && bL) break;
                    if (aL != bL) {
                        same = false;
                        break;
                    }
                    a++;
                    b++;
                }

                if (!same) recUpper++;
                recS[i] = recUpper;
            }

            int[] recSa = sais(recS, recUpper);
            for (int i = 0; i < m; i++)
                sortedLms[i] = lms[recSa[i]];

            inducedSort(s, sa, ls, sortedLms, sumL, sumS, upper);
        }

        return sa;
    }

    static boolean isLMS(int i, boolean[] ls) {
        return i > 0 && !ls[i - 1] && ls[i];
    }

    static int nextLMS(int i, boolean[] ls, int n) {
        for (int j = i + 1; j < n; j++)
            if (isLMS(j, ls)) return j;
        return n;
    }

    static void inducedSort(int[] s, int[] sa, boolean[] ls,
                            int[] lms, int[] sumL, int[] sumS, int upper) {
        Arrays.fill(sa, -1);

        int[] buf = new int[upper + 1];

        System.arraycopy(sumS, 0, buf, 0, upper + 1);
        for (int i = lms.length - 1; i >= 0; i--) {
            int d = lms[i];
            sa[buf[s[d]] - 1] = d;
            buf[s[d]]--;
        }

        System.arraycopy(sumL, 0, buf, 0, upper + 1);
        sa[buf[s.length - 1]++] = s.length - 1;

        for (int i = 0; i < s.length; i++) {
            int v = sa[i];
            if (v > 0 && !ls[v - 1])
                sa[buf[s[v - 1]]++] = v - 1;
        }

        System.arraycopy(sumL, 0, buf, 0, upper + 1);

        for (int i = s.length - 1; i >= 0; i--) {
            int v = sa[i];
            if (v > 0 && ls[v - 1])
                sa[--buf[s[v - 1]]] = v - 1;
        }
    }

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        int[] a = new int[s.length()];
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i);
            max = Math.max(max, a[i]);
        }

        int[] sa = sais(a, max);

        System.out.println("SA-IS Suffix Array:");
        for (int x : sa)
            System.out.println(x + " : " + s.substring(x));
    }
}
