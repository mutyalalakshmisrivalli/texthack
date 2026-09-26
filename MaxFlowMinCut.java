import java.util.*;

public class MaxFlowMinCut {
    static boolean bfs(int[][] r, int s, int t, int[] parent) {
        Arrays.fill(parent, -1);
        parent[s] = s;

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < r.length; v++) {
                if (parent[v] == -1 && r[u][v] > 0) {
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

        return parent[t] != -1;
    }

    public static void run(Scanner sc) {
        System.out.print("Number of vertices: ");
        int n = Integer.parseInt(sc.nextLine());

        int[][] capacity = new int[n][n];

        System.out.println("Enter capacity matrix:");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                capacity[i][j] = Integer.parseInt(sc.nextLine());

        System.out.print("Source: ");
        int s = Integer.parseInt(sc.nextLine());

        System.out.print("Sink: ");
        int t = Integer.parseInt(sc.nextLine());

        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++)
            residual[i] = capacity[i].clone();

        int flow = 0;
        int[] parent = new int[n];

        while (bfs(residual, s, t, parent)) {
            int path = Integer.MAX_VALUE;

            for (int v = t; v != s; v = parent[v])
                path = Math.min(path, residual[parent[v]][v]);

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= path;
                residual[v][u] += path;
            }

            flow += path;
        }

        boolean[] reachable = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        reachable[s] = true;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < n; v++) {
                if (!reachable[v] && residual[u][v] > 0) {
                    reachable[v] = true;
                    q.add(v);
                }
            }
        }

        System.out.println("Maximum Flow = " + flow);

        System.out.println("Min-Cut Edges:");

        int cutCapacity = 0;

        for (int u = 0; u < n; u++) {
            if (!reachable[u]) continue;

            for (int v = 0; v < n; v++) {
                if (!reachable[v] && capacity[u][v] > 0) {
                    System.out.println(u + " -> " + v +
                            " capacity = " + capacity[u][v]);
                    cutCapacity += capacity[u][v];
                }
            }
        }

        System.out.println("Minimum Cut Capacity = " + cutCapacity);
        System.out.println("Max-Flow = Min-Cut: " + (flow == cutCapacity));
    }
}
