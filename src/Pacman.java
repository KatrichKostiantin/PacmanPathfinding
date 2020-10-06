import javax.swing.*;
import java.awt.*;

public class Pacman{
    private int[] dx, dy;
    static final int PACMAN_ANIM_COUNT = 4;
    static final int PAC_ANIM_DELAY = 2;
    int pacAnimCount = PAC_ANIM_DELAY;
    int pacAnimDir = 1;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private int pacman_x, pacman_y, delta_pacman_x, delta_pacman_y;
    private int req_dx, req_dy, directionPacmanX, dilationPacmanY;
    private int pacmanAnimPos = 0;
    short[][] screenData;
    Board board;
    SearchPath searchPath;

    void setSearchPathMethod(SearchPath searchPath){
        this.searchPath = searchPath;
    }

    public Pacman(short[][] screenData, Board board) {
        this.screenData = screenData;
        this.board = board;
        init();
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
        Couple couple = searchPath.step();
            if (screenData[pacman_y][pacman_x] == 16) {
                win();
            }
    }

    private void win() {
        //TODO
    }



    public void draw(Graphics2D g2d) {
        pacmanAnimPos = pacmanAnimPos++ % PACMAN_ANIM_COUNT;
        if (directionPacmanX == -1) {
            drawPacnanLeft(g2d);
        } else if (directionPacmanX == 1) {
            drawPacmanRight(g2d);
        } else if (dilationPacmanY == -1) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }
    }

    private void drawPacmanUp(Graphics2D g2d) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2up, pacman_x + 1, pacman_y + 1, board);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacman_x + 1, pacman_y + 1, board);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacman_x + 1, pacman_y + 1, board);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, board);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2down, pacman_x + 1, pacman_y + 1, board);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacman_x + 1, pacman_y + 1, board);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacman_x + 1, pacman_y + 1, board);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, board);
                break;
        }
    }

    private void drawPacnanLeft(Graphics2D g2d) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2left, pacman_x + 1, pacman_y + 1, board);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacman_x + 1, pacman_y + 1, board);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacman_x + 1, pacman_y + 1, board);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, board);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {
        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2right, pacman_x + 1, pacman_y + 1, board);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacman_x + 1, pacman_y + 1, board);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacman_x + 1, pacman_y + 1, board);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, board);
                break;
        }
    }
}
