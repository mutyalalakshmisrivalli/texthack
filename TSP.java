import java.util.*;

public class TSP {
    public static void run(Scanner sc) {
        System.out.print("Number of cities: ");
        int n = Integer.parseInt(sc.nextLine());

        if (n > 20) {
            System.out.println("Use n <= 20 for bitmask DP.");
            return;
        }

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = Integer.parseInt(sc.nextLine());

        int N = 1 << n;
        long[][] dp = new long[N][n];

        for (long[] row : dp)
            Arrays.fill(row, Long.MAX_VALUE / 4);

        dp[1][0] = 0;

        for (int mask = 1; mask < N; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;

                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue;

                    int next = mask | (1 << v);

                    dp[next][v] = Math.min(dp[next][v],
                            dp[mask][u] + cost[u][v]);
                }
            }
        }

        long ans = Long.MAX_VALUE;

        for (int i = 1; i < n; i++)
            ans = Math.min(ans,
                    dp[N - 1][i] + cost[i][0]);

        System.out.println("Minimum TSP cost = " + ans);
    }
}
