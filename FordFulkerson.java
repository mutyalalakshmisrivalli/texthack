import java.util.*;

public class FordFulkerson {
    static int n;

    static boolean dfs(int[][] residual, int u, int t,
                       boolean[] visited, int[] parent) {
        visited[u] = true;

        if (u == t) return true;

        for (int v = 0; v < n; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                parent[v] = u;

                if (dfs(residual, v, t, visited, parent))
                    return true;
            }
        }

        return false;
    }

    static int maxFlow(int[][] graph, int s, int t) {
        n = graph.length;
        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++)
            residual[i] = graph[i].clone();

        int flow = 0;
        int[] parent = new int[n];

        while (true) {
            Arrays.fill(parent, -1);

            if (!dfs(residual, s, t, new boolean[n], parent))
                break;

            int pathFlow = Integer.MAX_VALUE;

            for (int v = t; v != s; v = parent[v])
                pathFlow = Math.min(pathFlow,
                        residual[parent[v]][v]);

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            flow += pathFlow;
        }

        return flow;
    }

    public static void run(Scanner sc) {
        System.out.print("Number of vertices: ");
        n = Integer.parseInt(sc.nextLine());

        int[][] graph = readGraph(sc, n);

        System.out.print("Source: ");
        int s = Integer.parseInt(sc.nextLine());

        System.out.print("Sink: ");
        int t = Integer.parseInt(sc.nextLine());

        System.out.println("Maximum Flow = " + maxFlow(graph, s, t));
    }

    static int[][] readGraph(Scanner sc, int n) {
        int[][] g = new int[n][n];

        System.out.println("Enter capacity matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                g[i][j] = Integer.parseInt(sc.nextLine());

        return g;
    }
}
