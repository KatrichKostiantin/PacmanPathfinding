package supporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DepthFirstPaths implements SearchPath {
    private final int startPosition;
    private int numOfKnot = 0;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstPaths(Graph G, int startPosition) {
        this.startPosition = startPosition;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, startPosition);
    }

    /**
     * ����� � �������
     *
     * @param G - ����
     * @param v - dfs � ������� v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * �� �������� ���� � v � s, �� ������ �������������
     *
     * @param v - ������� �� ��� ������ ����
     * @return true ���� � ����, false ���� ����
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * ������� ���� �� s �� v; null ���� ����� ����
     */
    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != startPosition; x = edgeTo[x])
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
