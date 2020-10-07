package supporting;

import java.util.Stack;

public class Couple {
    private Point point;
     private Stack<Point> stack;

    Couple(Point point, Stack<Point> stack) {
        this.point = point;
        this.stack = stack;
    }

    public Point getPoint() {
        return point;
    }

    public Stack<Point> getStack() {
        return stack;
    }
}
