import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pacman {
    static final int PACMAN_ANIM_COUNT = 4;
    static final int PAC_ANIM_DELAY = 2;
    short[][] screenData;
    Board board;
    SearchPath searchPath;
    int pacmanStep = 0;
    List<Integer> path;
    int animationCount = 0;
    private int[] dx, dy;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private int pacman_x, pacman_y, delta_pacman_x, delta_pacman_y, finishPoint;
    private int directionPacmanX, dilationPacmanY;
    private int pacmanAnimPos = 0;

    public Pacman(SearchPath searchPath, int finishPoint) {
        path = searchPath.pathTo(finishPoint);
        init();
    }

    void setBoard(Board board, short[][] screenData) {
        this.board = board;
        this.screenData = screenData;
    }

    void init() {
        initPacmanImages();
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
        int point = 0;
        if (pacmanStep < path.size()) {
            point = path.get(pacmanStep++);
            movePacmanTo(point / 16, point % 16);
        }
    }

    private void movePacmanTo(int j, int i) {
        directionPacmanX = (i - pacman_x);
        dilationPacmanY = (j - pacman_y);

        pacman_x = i;
        pacman_y = j;
        System.out.println("pacman_x: " + pacman_x + ", pacman_y: " + pacman_y);
    }

    // region eating
    public void drawPacmanEating(Graphics2D g2d) {
        pacmanAnimPos++;
        pacmanAnimPos %= PACMAN_ANIM_COUNT;
        if (directionPacmanX == -1) {
            drawPacmanEatingLeft(g2d, pacman_x * Board.BLOCK_SIZE, pacman_y * Board.BLOCK_SIZE);
        } else if (directionPacmanX == 1) {
            drawPacmanEatingRight(g2d, pacman_x * Board.BLOCK_SIZE, pacman_y * Board.BLOCK_SIZE);
        } else if (dilationPacmanY == -1) {
            drawPacmanEatingUp(g2d, pacman_x * Board.BLOCK_SIZE, pacman_y * Board.BLOCK_SIZE);
        } else {
            drawPacmanEatingDown(g2d, pacman_x * Board.BLOCK_SIZE, pacman_y * Board.BLOCK_SIZE);
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
        animationCount %= 5;
        if (animationCount++ == 0)
            movePacman();
    }
    //end region
}
