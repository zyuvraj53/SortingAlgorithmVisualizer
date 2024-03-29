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

    MyPanel() {

        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // adding the left panel, adding this first, as JPanel used FlowLayout by
        // default
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 600));
        leftPanel.setBackground(new Color(139, 84, 196));
        leftPanel.setLayout(new GridBagLayout());
        this.add(leftPanel);

        // adding radio button on the left panel
        random = new JRadioButton("Random");
        nearlySorted = new JRadioButton("Nearly Sorted");
        reversed = new JRadioButton("Reversed");
        fewUnique = new JRadioButton("Few Unique");


        buttonGroup = new ButtonGroup();
        buttonGroup.add(random);
        buttonGroup.add(nearlySorted);
        buttonGroup.add(reversed);
        buttonGroup.add(fewUnique);

        // GridBagLayout for the buttons:

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 200, 2);

        // col 0
        gbc.gridx = 0;

        // row 0
        gbc.gridy = 0;

        // increases components width by 10 pixels
        gbc.ipadx = 50;

        // increases components height by 50 pixels
        gbc.ipady = 20;

        leftPanel.add(SortingAlgorithms.sortsComboBox, gbc);

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 3;

        leftPanel.add(random, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(nearlySorted, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(reversed, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        leftPanel.add(fewUnique, gbc);

        // GridBagLayout for the buttons End


        // adding the animation panel
        AnimationPanel animationPanel = new AnimationPanel();
        isRandom = true;
        animationPanel.setPreferredSize(new Dimension(AnimationPanel.SCREEN_WIDTH, AnimationPanel.SCREEN_HEIGHT));

        // setting the action performed by the buttons
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRandom) {
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
                if (!isNearlySorted) {
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
                if (!isReversed) {
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
                if (!isFewUnique) {
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