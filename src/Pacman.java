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
        short ch;

        if (req_dx == -delta_pacman_x && req_dy == -delta_pacman_y) {
            delta_pacman_x = req_dx;
            delta_pacman_y = req_dy;
            directionPacmanX = delta_pacman_x;
            dilationPacmanY = delta_pacman_y;
        }

        if (pacman_x % Board.BLOCK_SIZE == 0 && pacman_y % Board.BLOCK_SIZE == 0) {
            ch = screenData[pacman_x][pacman_y];

            if ((ch & 16) != 0) {
                screenData[pacman_x][pacman_y] = (short) (ch & 15); //Eat point
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    delta_pacman_x = req_dx;
                    delta_pacman_y = req_dy;
                    directionPacmanX = delta_pacman_x;
                    dilationPacmanY = delta_pacman_y;
                }
            }

            // Check for standstill
            if ((delta_pacman_x == -1 && delta_pacman_y == 0 && (ch & 1) != 0)
                    || (delta_pacman_x == 1 && delta_pacman_y == 0 && (ch & 4) != 0)
                    || (delta_pacman_x == 0 && delta_pacman_y == -1 && (ch & 2) != 0)
                    || (delta_pacman_x == 0 && delta_pacman_y == 1 && (ch & 8) != 0)) {
                delta_pacman_x = 0;
                delta_pacman_y = 0;
            }
        }
        pacman_x = pacman_x + delta_pacman_x;
        pacman_y = pacman_y + delta_pacman_y;
    }


    public void draw(Graphics2D g2d) {
        doAnim();
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

    private void doAnim() {
        pacAnimCount--;

        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
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
