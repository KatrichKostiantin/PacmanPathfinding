package supporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DepthFirstPaths implements SearchPath {
    private final int startPosition;
    private int numOfKnot = 0;
    private boolean[] marked;
    private int[] edgeTo;
    private int stepsToFinish = 0;
    private int countOfSteps = 0;
    int endPosition;

    public DepthFirstPaths(Graph G, int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, startPosition);
    }

    /**
     * пошук в глибину
     *
     * @param G - граф
     * @param v - dfs з вершини v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;

                countOfSteps++;
                if(w == endPosition)
                    stepsToFinish = countOfSteps;

                dfs(G, w);
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
