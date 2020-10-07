package supporting;

import java.util.*;

public class TreeBFS implements SearchPath {
    ArrayDeque<Node> deque;
    private List<Point> marked;
    private Graph graph;
    private Point start, finish;
    Node root;

    public TreeBFS(Graph graph, Point start, Point finish) {
        this.graph = graph;
        this.start = start;
        this.finish = finish;
        root = new Node(null, start);
        marked = new ArrayList<>();
        deque = new ArrayDeque<>();
        deque.push(root);
    }

    @Override
    public Node getNextNode() {
        return deque.poll();
    }

    @Override
    public Queue<Point> getPathToNextPoint(Node start, Node nodeTo) {
        Queue<Point> resultStart = new LinkedList<>();
        Queue<Point> resultFinish = new LinkedList<>();
        Node tempStart = new Node(start);
        Node tempFinish = new Node(nodeTo);
        while (!tempStart.equals(tempFinish)) {
            if (tempStart.compareTo(tempFinish) < 0) {
                resultFinish.offer(tempFinish.value);
                tempFinish = tempFinish.father;
            } else if (tempStart.compareTo(tempFinish) > 0) {
                tempStart = tempStart.father;
                resultStart.add(tempStart.value);
            }
        }
        List<Point> list = (List) resultFinish;
        Collections.reverse(list);
        resultStart.addAll(list);
        return resultStart;
    }

    @Override
    public void addNewPoints(Node nodeTo) {
        if (nodeTo == null || nodeTo.value.equals(finish))
            return;
        for (Point point : graph.adj(nodeTo.value)) {
            if (!marked.contains(point)) {
                marked.add(point);
                Node newNode = new Node(nodeTo, point);
                deque.addLast(newNode);
                nodeTo.addChildren(newNode);
            }
        }
    }
}
