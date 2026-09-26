import java.util.*;

public class OBST {
    public static void run(Scanner sc) {
        System.out.print("Number of keys: ");
        int n = Integer.parseInt(sc.nextLine());

        int[] keys = new int[n];
        int[] freq = new int[n];

        System.out.println("Enter sorted keys:");
        for (int i = 0; i < n; i++)
            keys[i] = Integer.parseInt(sc.nextLine());

        System.out.println("Enter successful search frequencies:");
        for (int i = 0; i < n; i++)
            freq[i] = Integer.parseInt(sc.nextLine());

        int[][] dp = new int[n][n];
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + freq[i];

        for (int i = 0; i < n; i++)
            dp[i][i] = freq[i];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                int sum = prefix[j + 1] - prefix[i];

                for (int r = i; r <= j; r++) {
                    int left = r > i ? dp[i][r - 1] : 0;
                    int right = r < j ? dp[r + 1][j] : 0;

                    dp[i][j] = Math.min(dp[i][j],
                            left + right + sum);
                }
            }
        }

        System.out.println("Minimum OBST cost = " + dp[0][n - 1]);
    }
}
