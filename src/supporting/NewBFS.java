package supporting;

import java.util.*;

public class NewBFS implements NewSearchPath{
    ArrayDeque<Couple> deque;
    private List<Point> marked;
    private Graph graph;
    private Map<Point, List<Couple>> bfsGraph;
    private Point start, finish;
    private Stack<Point> pathToPoint;

    public NewBFS(Graph graph, Point start, Point finish) {
        this.graph = graph;
        this.start = start;
        this.finish = finish;
        deque = new ArrayDeque<>();
        marked = new ArrayList<>();
        bfsGraph = new HashMap<>();
        Stack<Point> stack = new Stack<>();
        deque.add(new Couple(start, stack));
    }

    @Override
    public Couple getNextStep() {
        Couple coupleNow = deque.poll();
        if (coupleNow == null || coupleNow.getPoint().equals(finish))
            return null;
        marked.add(coupleNow.getPoint());
        for (Point point : graph.adj(coupleNow.getPoint())) {
            if (!marked.contains(point)) {
                marked.add(point);
                Stack<Point> stack = new Stack<>();
                stack.push(point);
                //stack.push(couple.getPoint());
                Couple newCouple = new Couple(point, stack);
                bfsGraph.computeIfAbsent(coupleNow.getPoint(), k -> new ArrayList<>());
                bfsGraph.get(coupleNow.getPoint()).add(newCouple);
                deque.add(newCouple);
            }
        }

        return coupleNow;
    }

    @Override
    public void addOrRemovePathPoint(Point point) {
        if (deque.peek() == null)
            return;
        if (bfsGraph.get(deque.peek()).get(0).getStack().peek().equals(point)){

        }
        else
            deque.peek().getStack().push(point);
    }
}
