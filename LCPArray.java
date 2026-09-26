import java.util.*;

public class LCPArray {
    static int[] buildLCP(String s, int[] sa) {
        int n = s.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   s.charAt(i + h) == s.charAt(j + h))
                h++;

            lcp[r] = h;

            if (h > 0) h--;
        }

        return lcp;
    }

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        int[] sa = SuffixArray.build(s);
        int[] lcp = buildLCP(s, sa);

        System.out.println("Suffix Array and LCP:");
        for (int i = 0; i < s.length(); i++)
            System.out.println(sa[i] + " : LCP = " + lcp[i]);
    }
}
