package supporting;

import java.util.Queue;
import java.util.Stack;

public class Couple {
    private Point point;
     private Queue<Point> stack;

    Couple(Point point, Queue<Point> stack) {
        this.point = point;
        this.stack = stack;
    }

    public Point getPoint() {
        return point;
    }

    public Queue<Point> getStack() {
        return stack;
    }
}
