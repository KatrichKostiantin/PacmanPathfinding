import java.util.ArrayDeque;
import java.util.Stack;

public class BreadthFirstPaths implements SearchPath{
    private final int startPosition;
    ArrayDeque<Couple> deque;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private Point[] coords;
    private int numOfKnot = 0;
    private Graph graph;

    public BreadthFirstPaths(Graph G, int startPosition) {
        this.graph = G;
        this.startPosition = startPosition;
        deque = new ArrayDeque<>();
        coords = G.COORDS();
        distTo = new int[graph.V()];
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        bfs();
    }

    private void bfs() {
        deque.add(new Couple(0 , startPosition));
        marked[startPosition] = true;
        distTo[startPosition] = 0;
    }

    public Couple step() {
        //while (!deque.isEmpty()) {
        Couple point = deque.poll();
        for (int w : graph.adj(point.v)) {
            if (!marked[w]) {
                deque.add(new Couple(point.v, w));
                marked[w] = true;
                edgeTo[w] = point.v;
                distTo[w] = distTo[point.v] + 1;
            }
        }
        return point;
        //}
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
        for (int x = v; x != startPosition; x = edgeTo[x])
            path.push(x);
        path.push(startPosition);
        return path;
    }
}
