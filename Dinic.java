import java.util.*;

public class Dinic {
    static class Edge {
        int to, rev, cap;

        Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
        }
    }

    static List<Edge>[] graph;
    static int[] level, ptr;

    static void addEdge(int u, int v, int cap) {
        Edge a = new Edge(v, graph[v].size(), cap);
        Edge b = new Edge(u, graph[u].size(), 0);

        graph[u].add(a);
        graph[v].add(b);
    }

    static boolean bfs(int s, int t) {
        Arrays.fill(level, -1);

        Queue<Integer> q = new LinkedList<>();
        level[s] = 0;
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (Edge e : graph[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }

        return level[t] != -1;
    }

    static int dfs(int u, int t, int pushed) {
        if (pushed == 0) return 0;
        if (u == t) return pushed;

        for (; ptr[u] < graph[u].size(); ptr[u]++) {
            Edge e = graph[u].get(ptr[u]);

            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                int tr = dfs(e.to, t, Math.min(pushed, e.cap));

                if (tr > 0) {
                    e.cap -= tr;
                    graph[e.to].get(e.rev).cap += tr;
                    return tr;
                }
            }
        }

        return 0;
    }

    static int maxFlow(int s, int t) {
        int flow = 0;

        while (bfs(s, t)) {
            Arrays.fill(ptr, 0);

            int pushed;

            while ((pushed = dfs(s, t, Integer.MAX_VALUE)) > 0)
                flow += pushed;
        }

        return flow;
    }

    public static void run(Scanner sc) {
        System.out.print("Number of vertices: ");
        int n = Integer.parseInt(sc.nextLine());

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        System.out.print("Number of edges: ");
        int m = Integer.parseInt(sc.nextLine());

        System.out.println("Enter edges: from to capacity");

        for (int i = 0; i < m; i++) {
            String[] x = sc.nextLine().trim().split("\\s+");

            int u = Integer.parseInt(x[0]);
            int v = Integer.parseInt(x[1]);
            int cap = Integer.parseInt(x[2]);

            addEdge(u, v, cap);
        }

        System.out.print("Source: ");
        int s = Integer.parseInt(sc.nextLine());

        System.out.print("Sink: ");
        int t = Integer.parseInt(sc.nextLine());

        level = new int[n];
        ptr = new int[n];

        System.out.println("Dinic Maximum Flow = " + maxFlow(s, t));
    }
}
