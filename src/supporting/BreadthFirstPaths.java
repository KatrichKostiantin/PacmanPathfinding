package supporting;

import java.util.*;

public class BreadthFirstPaths implements SearchPath {
    public final int startPosition;
    public ArrayDeque<Couple> deque;
    public boolean[] marked;
    public int[] edgeTo;
    public int[] distTo;
    public Point[] coords;
    public int numOfKnot = 0;
    public Graph graph;
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
