import java.util.ArrayDeque;
import java.util.Stack;

public class BreadthFirstPaths {
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private Point[] coords;
    private int numOfKnot = 0;

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        coords = G.COORDS();
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public int getNum() {
        return numOfKnot;
    }

    /**
     * Чи присутній шлях з v в s, що задана конструктором
     *
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * повертає шлях між s та v; null якщо шляху немає
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
