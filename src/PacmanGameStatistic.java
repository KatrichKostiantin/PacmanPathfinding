import supporting.BreadthFirstPaths;
import supporting.DepthFirstPaths;
import supporting.Graph;
import supporting.Point;

import java.time.Instant;
import java.util.Random;

public class PacmanGameStatistic {
    private static final int COUNT_ITERATION = 20;
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
        Point randomEnd = searchEmptyPoint(levelData);
        levelData[randomEnd.y][randomEnd.x] = 16;
        Point randomStart = searchEmptyPoint(levelData);

        pacman = new Pacman(new DepthFirstPaths(mainGraph, randomStart.y * levelData.length + randomStart.x, randomEnd.y * levelData.length + randomEnd.x),
                randomStart);
        Board board = new Board(levelData);
        pacman.setBoard(board);
        return pacman.getCountOfStepsToFind();
    }

    private int iterationBFS(Graph mainGraph) {
        Point randomEnd = searchEmptyPoint(levelData);
        levelData[randomEnd.y][randomEnd.x] = 16;
        Point randomStart = searchEmptyPoint(levelData);

        pacman = new Pacman(new BreadthFirstPaths(mainGraph, randomStart.y * levelData.length + randomStart.x, randomEnd.y * levelData.length + randomEnd.x),
                randomStart);
        Board board = new Board(levelData);
        pacman.setBoard(board);
        return pacman.getCountOfStepsToFind();
    }

    private Graph buildGraphOnMatrix(short[][] levelData) {
        Graph graph = new Graph(levelData.length * levelData[0].length);
        for (int y = 0; y < levelData.length - 1; y++) {
            for (int x = 0; x < levelData[y].length - 1; x++) {
                if (levelData[y][x] == 0) {
                    if (levelData[y][x + 1] == 0)
                        graph.addEdge((y) * levelData.length + x, (y) * levelData.length + x + 1);
                    if (levelData[y + 1][x] == 0)
                        graph.addEdge((y) * levelData.length + x, (y + 1) * levelData.length + x);
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
