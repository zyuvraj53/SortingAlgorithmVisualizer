import javax.swing.*;
// When null layout manager is being used, then setSize or setBounds works, if layout manager is specified then
// setXXXSize() is used.
public class MyFrame extends JFrame {
    MyFrame() {
        this.setTitle("Sorting Algorithm Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new MyPanel());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
