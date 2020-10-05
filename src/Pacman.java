import javax.swing.*;
import java.awt.*;

public class Pacman extends JFrame {

    public Pacman() {

        initUI();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Pacman();
            ex.setVisible(true);
        });
    }

    private void initUI() {

        add(new Board());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }
}
