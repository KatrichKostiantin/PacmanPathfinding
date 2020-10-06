import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
    static final int BLOCK_SIZE = 40;
    static final int N_BLOCKS = 15;
    static final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    static final int POINT_SIZE = 6;
    final Color dotColor = new Color(192, 192, 0);
    final short[] levelData = {
            19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
            25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
            1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
            1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
            1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
            1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
            9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
    };
    Dimension d;
    Image image;
    Color mazeColor;
    boolean inGame = true;
    int pacsLeft, score;
    int currentSpeed = 3;
    Timer timer;
    short[] screenData;
    Pacman pacman;

    public Board() {
        initVariables();
        initBoard();
    }

    private void initBoard() {
        screenData = new short[Board.N_BLOCKS * Board.N_BLOCKS];
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        pacman = new Pacman(screenData, this);
        pacman.initPacmanImages();
    }

    private void initVariables() {
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);
        //dx = new int[4];
        //dy = new int[4];

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    /*
*/
    private void playGame(Graphics2D g2d) {
        pacman.draw(g2d);
        checkMaze();
    }

    private void checkMaze() {
        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {
            if ((screenData[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillOval(x + BLOCK_SIZE/2 - POINT_SIZE/2, y + BLOCK_SIZE/2 - POINT_SIZE/2, POINT_SIZE, POINT_SIZE);
                }

                i++;
            }
        }
    }

    private void initGame() {
        pacsLeft = 3;
        score = 0;
        initLevel();
        currentSpeed = 3;
    }

    private void initLevel() {
        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        playGame(g2d);

        g2d.drawImage(image, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }


    private void stop() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_PAUSE || key == KeyEvent.VK_ESCAPE)
                stop();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}