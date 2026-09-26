import java.util.*;

public class KasaiAlgorithm {
    public static int[] kasai(String s, int[] sa) {
        int n = s.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;

        int k = 0;

        for (int i = 0; i < n; i++) {
            if (rank[i] == 0) {
                k = 0;
                continue;
            }

            int j = sa[rank[i] - 1];

            while (i + k < n && j + k < n &&
                   s.charAt(i + k) == s.charAt(j + k))
                k++;

            lcp[rank[i]] = k;

            if (k > 0) k--;
        }

        return lcp;
    }

    public static void run(Scanner sc) {
        System.out.print("Enter text: ");
        String s = sc.nextLine();

        int[] sa = SuffixArray.build(s);
        int[] lcp = kasai(s, sa);

        System.out.println("Kasai LCP Array:");
        System.out.println(Arrays.toString(lcp));
    }
}
