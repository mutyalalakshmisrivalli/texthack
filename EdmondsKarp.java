import java.util.*;

public class EdmondsKarp {
    static int maxFlow(int[][] graph, int s, int t) {
        int n = graph.length;
        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++)
            residual[i] = graph[i].clone();

        int flow = 0;

        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            parent[s] = s;

            Queue<Integer> q = new LinkedList<>();
            q.add(s);

            while (!q.isEmpty() && parent[t] == -1) {
                int u = q.poll();

                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && residual[u][v] > 0) {
                        parent[v] = u;
                        q.add(v);
                    }
                }
            }

            if (parent[t] == -1) break;

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
        int n = Integer.parseInt(sc.nextLine());

        int[][] graph = new int[n][n];

        System.out.println("Enter capacity matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = Integer.parseInt(sc.nextLine());

        System.out.print("Source: ");
        int s = Integer.parseInt(sc.nextLine());

        System.out.print("Sink: ");
        int t = Integer.parseInt(sc.nextLine());

        System.out.println("Edmonds-Karp Maximum Flow = " +
                maxFlow(graph, s, t));
    }
}
