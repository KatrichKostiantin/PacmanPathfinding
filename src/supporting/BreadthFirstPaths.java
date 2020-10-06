package supporting;

import java.util.*;

public class BreadthFirstPaths implements SearchPath {
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
        deque.add(new Couple(0, startPosition));
        marked[startPosition] = true;
        distTo[startPosition] = 0;
        while (!deque.isEmpty()) {
            Couple point = deque.poll();
            for (int w : graph.adj(point.v)) {
                if (!marked[w]) {
                    deque.add(new Couple(point.v, w));
                    marked[w] = true;
                    edgeTo[w] = point.v;
                    distTo[w] = distTo[point.v] + 1;
                }
            }
        }
    }

   /* public supporting.Couple step() {
        //while (!deque.isEmpty()) {
        supporting.Couple point = deque.poll();
        for (int w : graph.adj(point.v)) {
            if (!marked[w]) {
                deque.add(new supporting.Couple(point.v, w));
                marked[w] = true;
                edgeTo[w] = point.v;
                distTo[w] = distTo[point.v] + 1;
            }
        }
        return point;
        //}
    }*/


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
    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        path.add(v);
        for (int x = v; x != startPosition; x = edgeTo[x])
            path.add(x);
        path.add(startPosition);
        Collections.reverse(path);
        return path;
    }
}
