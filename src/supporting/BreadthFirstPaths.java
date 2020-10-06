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
    private int stepsToFinish = 0;
    private int countOfSteps = 0;
    int endPosition;

    public BreadthFirstPaths(Graph G, int startPosition, int endPosition) {
        this.graph = G;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
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
                    countOfSteps++;
                    if(w == endPosition)
                        stepsToFinish = countOfSteps;
                }
            }
        }
    }

    public int getStepsToFinish() {
        return stepsToFinish;
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
    public List<Integer> pathToFinish() {
        if (!hasPathTo(endPosition)) return null;
        List<Integer> path = new ArrayList<>();
        path.add(endPosition);
        for (int x = endPosition; x != startPosition; x = edgeTo[x])
            path.add(x);
        path.add(startPosition);
        Collections.reverse(path);
        return path;
    }
}
