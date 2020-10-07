package supporting;

import java.util.Queue;

public interface SearchPath {
    Node getNextNode();
    Queue<Point> getPathToNextPoint(Node start, Node nodeTo);
    void addNewPoints(Node nodeTo);
    int getSteps();
}
