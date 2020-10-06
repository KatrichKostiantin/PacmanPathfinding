import javax.swing.*;
import java.awt.*;

public class PacmanGame extends JFrame {

    public PacmanGame() {
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new PacmanGame();
            ex.setVisible(true);
        });
    }

    private void initUI() {
        Board board = new Board();
        add(board);
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((Board.N_BLOCKS + 1)* Board.BLOCK_SIZE, (Board.N_BLOCKS + 2) * Board.BLOCK_SIZE);
        setLocationRelativeTo(null);
    }
}
