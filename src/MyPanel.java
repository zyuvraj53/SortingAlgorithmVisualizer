import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    JPanel leftPanel;
    MyPanel() throws InterruptedException {

        this.setPreferredSize(new Dimension(800, 600));

        // adding the left panel, adding this first, as JPanel used FlowLayout by default
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 600));
        leftPanel.setBackground(new Color(255, 0, 0));
        this.add(leftPanel);

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        RightPanel rightPanel = new RightPanel();
        rightPanel.setPreferredSize(new Dimension(RightPanel.SCREEN_WIDTH, RightPanel.SCREEN_HEIGHT));
        this.add(rightPanel);
    }
}