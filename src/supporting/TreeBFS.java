package supporting;

import java.util.*;

public class TreeBFS implements SearchPath {
    ArrayDeque<Node> deque;
    private List<Point> marked;
    private Graph graph;
    private Point finish;
    private int steps;
    private Node root;

    public Point getFinish() {
        return finish;
    }

    public TreeBFS(Graph graph, Point start, Point finish) {
        this.graph = graph;
        this.finish = finish;
        root = new Node(null, start);
        marked = new ArrayList<>();
        deque = new ArrayDeque<>();
        deque.push(root);
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public Node getNextNode() {
        return deque.poll();
    }

    @Override
    public Queue<Point> getPathToNextPoint(Node start, Node nodeTo) {
        if(start.value.equals(finish))
            return null;
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
        steps++;
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
