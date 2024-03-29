import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MyPanel extends JPanel {

    JPanel leftPanel;

    ButtonGroup buttonGroup;

    JRadioButton random;
    JRadioButton nearlySorted;
    JRadioButton reversed;
    JRadioButton fewUnique;

    JSlider slider;
    JTextField sliderValueTextField;

    static boolean isRandom;
    static boolean isNearlySorted;
    static boolean isReversed;
    static boolean isFewUnique;

    MyPanel() {

        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // adding the animation panel
        AnimationPanel animationPanel = new AnimationPanel();
        isRandom = true;
        animationPanel.setPreferredSize(new Dimension(AnimationPanel.SCREEN_WIDTH, AnimationPanel.SCREEN_HEIGHT));

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

        slider = new JSlider(2, 200, 100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);

        sliderValueTextField = new JTextField(100);
        sliderValueTextField.setEditable(false);
        sliderValueTextField.setText(AnimationPanel.noOfRects + "");

        // GridBagLayout for the buttons, JComboBox and JSlider:

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 50, 200, 2);

        // col 0
        gbc.gridx = 0;

        // row 0
        gbc.gridy = 0;

        // width
        gbc.ipadx = 50;

        // height
        gbc.ipady = 20;

        leftPanel.add(SortingAlgorithms.sortsComboBox, gbc);

        gbc.insets = new Insets(2, 50, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 1;

        leftPanel.add(random, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(nearlySorted, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(reversed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(fewUnique, gbc);

        gbc.insets = new Insets(50, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(slider, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        leftPanel.add(sliderValueTextField, gbc);

        // GridBagLayout for the buttons ,JComboBox and JSlider End

        slider.addChangeListener(new ChangeListener() {

            int[] factors_of_600 = {1, 2, 3, 4, 5, 6, 8, 10, 12, 15, 20, 24, 25, 30, 40, 50, 60, 75, 100, 120, 150, 200, 300, 600};

            public int lowerBound(int sliderValue){
                int index = Arrays.binarySearch(factors_of_600, sliderValue);

                if(index < 0){
                    return Math.abs(index) - 1;
                }else{
                    while(index > 0){
                        if(factors_of_600[index - 1] == sliderValue)
                            index--;
                        else
                            return index;
                    }
                    return index;
                }
            }

            @Override
            public void stateChanged(ChangeEvent e){
                int sliderValue = slider.getValue();
                AnimationPanel.noOfRects = factors_of_600[lowerBound(sliderValue)];
                animationPanel.rectanglesArrayInit();
                animationPanel.revalidate();
                repaint();
                animationPanel.randomize();
                sliderValueTextField.setText(AnimationPanel.noOfRects + "");
            }
        });


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