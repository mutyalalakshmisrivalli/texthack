import java.util.*;

public class LocalSequenceAlignment {
    public static void run(Scanner sc) {
        System.out.print("Sequence 1: ");
        String a = sc.nextLine();
        System.out.print("Sequence 2: ");
        String b = sc.nextLine();

        System.out.print("Match score: ");
        int match = Integer.parseInt(sc.nextLine());
        System.out.print("Mismatch score: ");
        int mismatch = Integer.parseInt(sc.nextLine());
        System.out.print("Gap penalty: ");
        int gap = Integer.parseInt(sc.nextLine());

        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        int best = 0, bi = 0, bj = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int diag = dp[i - 1][j - 1]
                        + (a.charAt(i - 1) == b.charAt(j - 1) ? match : mismatch);

                dp[i][j] = Math.max(0, Math.max(diag,
                        Math.max(dp[i - 1][j] + gap,
                                 dp[i][j - 1] + gap)));

                if (dp[i][j] > best) {
                    best = dp[i][j];
                    bi = i;
                    bj = j;
                }
            }
        }

        StringBuilder x = new StringBuilder();
        StringBuilder y = new StringBuilder();

        int i = bi, j = bj;

        while (i > 0 && j > 0 && dp[i][j] > 0) {
            if (dp[i][j] == dp[i - 1][j - 1] +
                (a.charAt(i - 1) == b.charAt(j - 1) ? match : mismatch)) {
                x.append(a.charAt(--i));
                y.append(b.charAt(--j));
            } else if (dp[i][j] == dp[i - 1][j] + gap) {
                x.append(a.charAt(--i));
                y.append('-');
            } else {
                x.append('-');
                y.append(b.charAt(--j));
            }
        }

        System.out.println("Local Alignment Score = " + best);
        System.out.println("Alignment 1: " + x.reverse());
        System.out.println("Alignment 2: " + y.reverse());
    }
}
