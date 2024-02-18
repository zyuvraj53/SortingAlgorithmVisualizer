import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    JPanel leftPanel;

    ButtonGroup buttonGroup;
    JRadioButton random;
    JRadioButton nearlySorted;
    JRadioButton reversed;
    JRadioButton fewUnique;

    static boolean isRandom;
    static boolean isNearlySorted;
    static boolean isReversed;
    static boolean isFewUnique;

    MyPanel() throws InterruptedException {

        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // adding the left panel, adding this first, as JPanel used FlowLayout by default
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 600));
        leftPanel.setBackground(new Color(139, 84, 196));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        this.add(leftPanel);

        // adding radio button on the left panel
        random = new JRadioButton("Random");
        nearlySorted = new JRadioButton("Nearly Sorted");
        reversed = new JRadioButton("Reversed");
        fewUnique = new JRadioButton("Few Unique");

        random.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 100), 20));

        buttonGroup = new ButtonGroup();
        buttonGroup.add(random);
        buttonGroup.add(nearlySorted);
        buttonGroup.add(reversed);
        buttonGroup.add(fewUnique);

        leftPanel.add(random);
        leftPanel.add(nearlySorted);
        leftPanel.add(reversed);
        leftPanel.add(fewUnique);

        //adding the animation panel
        AnimationPanel animationPanel = new AnimationPanel();
        isRandom = true;
        animationPanel.setPreferredSize(new Dimension(AnimationPanel.SCREEN_WIDTH, AnimationPanel.SCREEN_HEIGHT));

        // setting the action performed by the buttons
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isRandom){
                    animationPanel.randomize();
                    isRandom = true;
                    isNearlySorted = false;
                    isReversed = false;
                    isFewUnique = false;
                }
            }
        });
        nearlySorted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isNearlySorted){
                    animationPanel.nearlyRandomize();
                    isRandom = false;
                    isNearlySorted = true;
                    isReversed = false;
                    isFewUnique = false;
                }
            }
        });
        reversed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isReversed){
                    animationPanel.reverse();
                    isRandom = false;
                    isNearlySorted = false;
                    isReversed = true;
                    isFewUnique = false;
                }
            }
        });
        fewUnique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isFewUnique){
                    animationPanel.makeFewUnique();
                    isRandom = false;
                    isNearlySorted = false;
                    isReversed = false;
                    isFewUnique = true;
                }
            }
        });
        this.add(animationPanel);
    }
}