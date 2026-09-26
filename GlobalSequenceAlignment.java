import java.util.*;

public class GlobalSequenceAlignment {
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

        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + gap;
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] + gap;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                int diag = dp[i - 1][j - 1]
                        + (a.charAt(i - 1) == b.charAt(j - 1) ? match : mismatch);
                dp[i][j] = Math.max(diag,
                        Math.max(dp[i - 1][j] + gap,
                                 dp[i][j - 1] + gap));
            }

        StringBuilder x = new StringBuilder();
        StringBuilder y = new StringBuilder();

        int i = n, j = m;

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 &&
                dp[i][j] == dp[i - 1][j - 1] +
                (a.charAt(i - 1) == b.charAt(j - 1) ? match : mismatch)) {
                x.append(a.charAt(--i));
                y.append(b.charAt(--j));
            } else if (i > 0 && dp[i][j] == dp[i - 1][j] + gap) {
                x.append(a.charAt(--i));
                y.append('-');
            } else {
                x.append('-');
                y.append(b.charAt(--j));
            }
        }

        System.out.println("Score = " + dp[n][m]);
        System.out.println("Alignment 1: " + x.reverse());
        System.out.println("Alignment 2: " + y.reverse());
    }
}
