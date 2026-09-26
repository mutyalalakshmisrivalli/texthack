import java.util.*;

public class HamiltonianPath {
    public static void run(Scanner sc) {
        System.out.print("Number of vertices: ");
        int n = Integer.parseInt(sc.nextLine());

        if (n > 20) {
            System.out.println("Use n <= 20 for bitmask DP.");
            return;
        }

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix (0/1):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = Integer.parseInt(sc.nextLine());

        int N = 1 << n;
        boolean[][] dp = new boolean[N][n];

        for (int i = 0; i < n; i++)
            dp[1 << i][i] = true;

        for (int mask = 1; mask < N; mask++) {
            for (int u = 0; u < n; u++) {
                if (!dp[mask][u]) continue;

                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0 && graph[u][v] != 0)
                        dp[mask | (1 << v)][v] = true;
                }
            }
        }

        boolean found = false;

        for (int i = 0; i < n; i++)
            if (dp[N - 1][i]) found = true;

        System.out.println(found ?
                "Hamiltonian Path Exists." :
                "Hamiltonian Path Does Not Exist.");
    }
}
