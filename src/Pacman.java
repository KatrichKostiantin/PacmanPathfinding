import supporting.Point;
import supporting.SearchPath;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pacman {
    static final int PACMAN_ANIM_COUNT = 4;
    private static final int ANIMATION_STEPS = 5;
    Board board;
    List<Integer> path;
    int animationCount = 0;
    int pacmanStep = 0;
    int additionAnimationX = 0;
    int additionAnimationY = 0;
    Point startPosition;
    SearchPath searchPath;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private int pacman_x, pacman_y;
    private int directionPacmanX, directionPacmanY;
    private int pacmanAnimPos = 0;

    public Pacman(SearchPath searchPath, Point startPosition) {
        this.searchPath = searchPath;
        this.startPosition = startPosition;
        path = searchPath.pathToFinish();
        if (path == null) {
            System.out.println("ERROR");
            return;
        }
        init();
    }

    void setBoard(Board board) {
        this.board = board;
    }

    void init() {
        initPacmanImages();
        movePacmanTo(startPosition.y, startPosition.x);
    }

    void initPacmanImages() {
        pacman1 = new ImageIcon("images/pacman.png").getImage();
        pacman2up = new ImageIcon("images/up1.png").getImage();
        pacman3up = new ImageIcon("images/up2.png").getImage();
        pacman4up = new ImageIcon("images/up3.png").getImage();
        pacman2down = new ImageIcon("images/down1.png").getImage();
        pacman3down = new ImageIcon("images/down2.png").getImage();
        pacman4down = new ImageIcon("images/down3.png").getImage();
        pacman2left = new ImageIcon("images/left1.png").getImage();
        pacman3left = new ImageIcon("images/left2.png").getImage();
        pacman4left = new ImageIcon("images/left3.png").getImage();
        pacman2right = new ImageIcon("images/right1.png").getImage();
        pacman3right = new ImageIcon("images/right2.png").getImage();
        pacman4right = new ImageIcon("images/right3.png").getImage();
    }

    void movePacman() {
        int point;
        if (pacmanStep < path.size()) {
            point = path.get(pacmanStep++);
            movePacmanTo(point / 16, point % 16);
        } else
            board.stop();
    }

    private void movePacmanTo(int j, int i) {
        directionPacmanX = (i - pacman_x);
        directionPacmanY = (j - pacman_y);

        pacman_x = i;
        pacman_y = j;
    }

    public void drawPacmanEating(Graphics2D g2d) {
        additionAnimationX = -1 * directionPacmanX * (ANIMATION_STEPS - animationCount) * (Board.BLOCK_SIZE / ANIMATION_STEPS);
        additionAnimationY = -1 * directionPacmanY * (ANIMATION_STEPS - animationCount) * (Board.BLOCK_SIZE / ANIMATION_STEPS);
        pacmanAnimPos++;
        pacmanAnimPos %= PACMAN_ANIM_COUNT;
        if (directionPacmanX == -1) {
            drawPacmanEatingLeft(g2d, pacman_x * Board.BLOCK_SIZE + additionAnimationX, pacman_y * Board.BLOCK_SIZE + additionAnimationY);
        } else if (directionPacmanX == 1) {
            drawPacmanEatingRight(g2d, pacman_x * Board.BLOCK_SIZE + additionAnimationX, pacman_y * Board.BLOCK_SIZE + additionAnimationY);
        } else if (directionPacmanY == -1) {
            drawPacmanEatingUp(g2d, pacman_x * Board.BLOCK_SIZE + additionAnimationX, pacman_y * Board.BLOCK_SIZE + additionAnimationY);
        } else {
            drawPacmanEatingDown(g2d, pacman_x * Board.BLOCK_SIZE + additionAnimationX, pacman_y * Board.BLOCK_SIZE + additionAnimationY);
        }
    }

    private void drawPacmanEatingUp(Graphics2D g2d, int x, int y) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2up, x, y, board);
                break;
            case 2:
                g2d.drawImage(pacman3up, x, y, board);
                break;
            case 3:
                g2d.drawImage(pacman4up, x, y, board);
                break;
            default:
                g2d.drawImage(pacman1, x, y, board);
                break;
        }
    }

    private void drawPacmanEatingDown(Graphics2D g2d, int x, int y) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2down, x, y, board);
                break;
            case 2:
                g2d.drawImage(pacman3down, x, y, board);
                break;
            case 3:
                g2d.drawImage(pacman4down, x, y, board);
                break;
            default:
                g2d.drawImage(pacman1, x, y, board);
                break;
        }
    }

    private void drawPacmanEatingLeft(Graphics2D g2d, int x, int y) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2left, x, y, board);
                break;
            case 2:
                g2d.drawImage(pacman3left, x, y, board);
                break;
            case 3:
                g2d.drawImage(pacman4left, x, y, board);
                break;
            default:
                g2d.drawImage(pacman1, x, y, board);
                break;
        }
    }

    private void drawPacmanEatingRight(Graphics2D g2d, int x, int y) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2right, x, y, board);
                break;
            case 2:
                g2d.drawImage(pacman3right, x, y, board);
                break;
            case 3:
                g2d.drawImage(pacman4right, x, y, board);
                break;
            default:
                g2d.drawImage(pacman1, x, y, board);
                break;
        }
    }

    public void step(Graphics2D g2d) {
        drawPacmanEating(g2d);
        animationCount %= ANIMATION_STEPS;
        if (animationCount++ == 0)
            movePacman();
    }

    public int getCountOfStepsToFind() {
        return searchPath.getStepsToFinish();
    }
}
