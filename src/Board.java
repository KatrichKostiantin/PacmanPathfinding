import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
    static final int BLOCK_SIZE = 40;
    static final int POINT_SIZE = 6;
    final Color dotColor = new Color(192, 192, 0);
    Dimension d;
    Color mazeColor;
    Timer timer;
    short[][] screenData;
    Pacman pacman;

    public Board(short[][] screenData) {
        this.screenData = screenData.clone();
        initVariables();
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        pacman = new Pacman(screenData, this);
        pacman.initPacmanImages();
    }

    private void initVariables() {
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
    }

    private void playGame(Graphics2D g2d) {
        pacman.draw(g2d);
        checkMaze();
    }

    private void checkMaze() {
    }

    private void drawMaze(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(mazeColor);
        g2d.drawRect(0, 0, screenData[0].length*BLOCK_SIZE, screenData.length * BLOCK_SIZE);
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData[i].length; j++) {
                int x = j * BLOCK_SIZE;
                int y = i * BLOCK_SIZE;
                g2d.setColor(mazeColor);

                if ((screenData[i][j] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i][j] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i][j] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i][j] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i][j] & 16) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillOval(x + BLOCK_SIZE / 2 - POINT_SIZE / 2, y + BLOCK_SIZE / 2 - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
                    pacman.setFinishPoint(i, j);
                }
            }
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

        drawMaze(g2d);
        playGame(g2d);

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