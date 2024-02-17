import javax.swing.*;

public class MyFrame extends JFrame {

    MyFrame() throws InterruptedException {
        this.setTitle("Sorting Algorithm Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new MyPanel());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
