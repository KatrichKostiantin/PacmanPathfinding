import supporting.Graph;
import supporting.Point;
import supporting.TreeBFS;
import supporting.TreeDFS;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PacmanGameStatistic {
    private static final int COUNT_ITERATION = 1000;
    final short[][] levelData = {
            {0, 0, 0, 5, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0, 0, 0, 15, 0, 3, 10, 10, 6, 0},
            {0, 7, 0, 5, 0, 7, 0, 15, 0, 0, 0, 5, 0, 0, 5, 0},
            {0, 5, 0, 13, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0},
            {0, 5, 0, 0, 0, 5, 0, 0, 0, 15, 0, 5, 0, 11, 12, 0},
            {0, 5, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
            {10, 8, 10, 10, 10, 12, 0, 15, 0, 0, 0, 9, 10, 10, 10, 10},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0},
            {0, 5, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 5, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 1, 14, 0, 0, 0, 11, 2, 2, 14, 0, 0, 0, 11, 4, 0},
            {0, 5, 0, 0, 0, 0, 0, 1, 4, 0, 0, 0, 0, 0, 5, 0},
            {0, 9, 10, 10, 14, 0, 0, 9, 12, 0, 0, 11, 10, 10, 12, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    Pacman pacman;
    Random random = new Random();

    public static void main(String[] args) {
        PacmanGameStatistic pacmanGameStatistic = new PacmanGameStatistic();
        pacmanGameStatistic.start();
    }

    private void start() {
        Graph mainGraph = buildGraphOnMatrix(levelData);
        long timeDFS, timeBFS, stepsDFS = 0, stepsBFS = 0;

        long timeStart = Instant.now().toEpochMilli();
        for (int i = 0; i < COUNT_ITERATION; i++) {
            stepsDFS += iterationDFS(mainGraph);
        }
        timeDFS = Instant.now().toEpochMilli() - timeStart;
        stepsDFS /= COUNT_ITERATION;

        timeStart = Instant.now().toEpochMilli();
        for (int i = 0; i < COUNT_ITERATION; i++) {
            stepsBFS += iterationBFS(mainGraph);
        }
        timeBFS = Instant.now().toEpochMilli() - timeStart;
        stepsBFS /= COUNT_ITERATION;

        System.out.println("RESULT OF " + COUNT_ITERATION + " ITERATION:\n" +
                "DFS: Time - " + timeDFS + ", steps - " + stepsDFS + "\n" +
                "BFS: Time - " + timeBFS + ", steps - " + stepsBFS);
    }

    private int iterationDFS(Graph mainGraph) {
        Graph newGraph = new Graph(mainGraph);
         short[][] newLevelData = copyLevelData(levelData);
        Point randomEnd = searchEmptyPoint(newLevelData);
        newLevelData[randomEnd.y][randomEnd.x] = 16;
        Point randomStart = searchEmptyPoint(newLevelData);

        pacman = new Pacman(new TreeDFS(newGraph, randomStart, randomEnd),
                randomStart, randomEnd);
        Board board = new Board(newLevelData);
        pacman.setBoard(board);
        return pacman.getCountOfStepsToFind();
    }

    private short[][] copyLevelData(short[][] array) {
        short[][] result = new short[array.length][array[0].length];
        for (int i = 0; i < array.length; i++)
            System.arraycopy(array[i], 0, result[i], 0, array[i].length);
        return result;
    }

    private int iterationBFS(Graph mainGraph) {
        Graph newGraph = new Graph(mainGraph);
        short[][] newLevelData = copyLevelData(levelData);
        Point randomEnd = searchEmptyPoint(newLevelData);
        newLevelData[randomEnd.y][randomEnd.x] = 16;
        Point randomStart = searchEmptyPoint(newLevelData);

        pacman = new Pacman(new TreeBFS(newGraph, randomStart, randomEnd),
                randomStart, randomEnd);
        Board board = new Board(newLevelData);
        pacman.setBoard(board);
        return pacman.getCountOfStepsToFind();
    }

    private Graph buildGraphOnMatrix(short[][] levelData) {
        Graph graph = new Graph(levelData.length * levelData[0].length);
        for (int y = 0; y < levelData.length; y++) {
            for (int x = 0; x < levelData[y].length; x++) {
                if (levelData[y][x] == 0) {
                    if (x + 1 < levelData[y].length && levelData[y][x + 1] == 0 )
                        graph.addEdge(new Point(x, y), new Point(x + 1, y));
                    if (y + 1 < levelData.length && levelData[y + 1][x] == 0)
                        graph.addEdge(new Point(x, y), new Point(x, y + 1));
                }
            }
        }
        return graph;
    }

    private Point searchEmptyPoint(short[][] levelData) {
        Point randomPoint;
        do {
            randomPoint = new Point(random.nextInt(15), random.nextInt(15));
            if (levelData[randomPoint.y][randomPoint.x] != 0)
                continue;

            levelData[randomPoint.y][randomPoint.x] = 16;
            break;
        } while (true);
        return randomPoint;
    }
}
