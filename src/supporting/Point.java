package supporting;

public class Point {
    final public int x;
    final public int y;

    public Point(int d, int e) {
        this.x = d;
        this.y = e;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}