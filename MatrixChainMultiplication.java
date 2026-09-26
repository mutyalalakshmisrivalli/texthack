import java.util.*;

public class MatrixChainMultiplication {
    static String build(int[][] split, int i, int j) {
        if (i == j) return "A" + i;

        int k = split[i][j];

        return "(" + build(split, i, k) +
                " x " + build(split, k + 1, j) + ")";
    }

    public static void run(Scanner sc) {
        System.out.print("Number of matrices: ");
        int n = Integer.parseInt(sc.nextLine());

        int[] p = new int[n + 1];

        System.out.println("Enter " + (n + 1) + " dimensions:");
        for (int i = 0; i <= n; i++)
            p[i] = Integer.parseInt(sc.nextLine());

        long[][] dp = new long[n + 1][n + 1];
        int[][] split = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j]
                            + (long)p[i - 1] * p[k] * p[j];

                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k;
                    }
                }
            }
        }

        System.out.println("Minimum multiplications = " + dp[1][n]);
        System.out.println("Optimal order = " + build(split, 1, n));
    }
}
