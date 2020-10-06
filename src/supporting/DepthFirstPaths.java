package supporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DepthFirstPaths implements SearchPath {
    public final int startPosition;
    public int numOfKnot = 0;
    public boolean[] marked;
    public int[] edgeTo;
    public Graph graph;
    public int stepsToFinish = 0;
    public int countOfSteps = 0;
    public int endPosition;

    public DepthFirstPaths(Graph G, int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs();
    }

    /**
     * пошук в глибину
     *
     * @param G - граф
     * @param v - dfs з вершини v
     */
    private void dfs() {
        marked[startPosition] = true;
        for (int w : graph.adj(startPosition)) {
            if (!marked[w]) {
                edgeTo[w] = startPosition;

                countOfSteps++;
                if(w == endPosition)
                    stepsToFinish = countOfSteps;

                dfs();
            }
        }
    }


    public int getStepsToFinish() {
        return stepsToFinish;
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
        for (int x = endPosition; x != startPosition; x = edgeTo[x])
            path.add(x);
        path.add(startPosition);
        numOfKnot = path.size();
        Collections.reverse(path);
        return path;
    }

    public int getNum() {
        return numOfKnot;
    }


}
