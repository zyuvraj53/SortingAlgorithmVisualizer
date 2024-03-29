import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AnimationPanel extends JPanel implements SortingAlgorithms, ActionListener {

    final static int SCREEN_WIDTH = 600;
    final static int SCREEN_HEIGHT = 600;
    final static int DELAY = 50;
    static int noOfRects;
    Rectangle[] rectangles;

    JButton playButton;

    static {
        noOfRects = 100;
    }

    AnimationPanel() {

        // The animation panel
        //this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //! this.setBounds(200, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setBackground(new Color(125, 50, 255));
        rectangles = new Rectangle[noOfRects];
        // randomizing all the rectangles;
        this.randomize();

        // making a button to play the animation
        playButton = new JButton();
        ImageIcon playIcon = new ImageIcon("C:\\Users\\Yuvraj\\Desktop\\Java\\SortingAlgorithmsVisualizer\\src\\playButton.png"); // load the image to a imageIcon
        Image playImage = playIcon.getImage(); // transform it
        Image newPlayImg = playImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newPlayImg);  // transform it back
        playButton.setIcon(playIcon);
        this.add(playButton);
        playButton.addActionListener(this);

    }

    public void randomize(){
        // setting a random height for all the rectangles
        Random random = new Random();
        for(int i = 0; i < noOfRects; i++){
            rectangles[i] = new Rectangle(random.nextInt(0, (SCREEN_HEIGHT)));
            System.out.println(rectangles[i].getHeight());
        }
        // setting width for all the rectangles
        Rectangle.width = SCREEN_WIDTH / noOfRects;
        repaint();
    }

    public void nearlyRandomize(){
//        Random random = new Random();
//        int random_increase = (int) SCREEN_HEIGHT / noOfRects;
//        for(int i = 0; i < noOfRects; i++){
//            rectangles[i] = new Rectangle(random.nextInt(random_increase * i/3, (random_increase * (i + 1))));
//            System.out.println(rectangles[i].getHeight());
//        }

        // making a random array
        Random random = new Random();
        for(int i = 0; i < noOfRects; i++){
            rectangles[i] = new Rectangle(random.nextInt(0, (SCREEN_HEIGHT)));
            System.out.println(rectangles[i].getHeight());
        }

        // nearly sorting the array
        for(int k = 1; k <= random.nextInt(noOfRects / 2, noOfRects - 1); k++) {
            int flag = 0;
            for (int i = random.nextInt(0, noOfRects - k - 1); i <= random.nextInt((noOfRects - k - 1) / 2, noOfRects - k - 1); i++) {
                if (rectangles[i].getHeight() > rectangles[i + 1].getHeight()) {
                    int temp = rectangles[i].getHeight();
                    rectangles[i].setHeight(rectangles[i + 1].getHeight());
                    rectangles[i + 1].setHeight(temp);
                    flag = 1;
                }
            }
            if(flag == 0)
                break;
        }

        // setting width for all the rectangles
        Rectangle.width = SCREEN_WIDTH / noOfRects;
        repaint();
    }

    public void makeFewUnique(){
        int[] fewUniqueArray = new int[(int) noOfRects / 3];
        Random random = new Random();
        for(int i = 0; i < fewUniqueArray.length; i++){
            fewUniqueArray[i] = random.nextInt(0, SCREEN_HEIGHT);
        }
        for(int i = 0; i < noOfRects; i++){
            rectangles[i] = new Rectangle(fewUniqueArray[random.nextInt(fewUniqueArray.length)]);
            System.out.println(rectangles[i].getHeight());
        }
        // setting width for all the rectangles
        Rectangle.width = SCREEN_WIDTH / noOfRects;
        repaint();
    }

    public void reverse(){
        this.randomize();
        for(int k = 1; k <= noOfRects - 1; k++) {
            int flag = 0;
            for (int i = 0; i <= noOfRects - k - 1; i++) {
                if (rectangles[i].getHeight() > rectangles[i + 1].getHeight()) {
                    int temp = rectangles[i].getHeight();
                    rectangles[i].setHeight(rectangles[i + 1].getHeight());
                    rectangles[i + 1].setHeight(temp);
                    flag = 1;
                }
            }
            if(flag == 0)
                break;
        }

        for (int i = 0; i < noOfRects / 2; i++) {
            Rectangle temp = rectangles[i];
            rectangles[i] = rectangles[noOfRects - i - 1];
            rectangles[noOfRects - i - 1] = temp;
        }
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawRectangles(g);
    }

    public void drawRectangles(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        for(int i = 0; i < noOfRects; i++){
            g2D.setColor(new Color(50, 50, 50));
            g2D.fillRect(i * Rectangle.width, SCREEN_HEIGHT - rectangles[i].getHeight(), Rectangle.width, rectangles[i].getHeight());
            g2D.setColor(new Color(10, 10, 10));
            g2D.drawRect(i * Rectangle.width, SCREEN_HEIGHT - rectangles[i].getHeight(), Rectangle.width, rectangles[i].getHeight());
        }
    }

    @Override
    public void bubbleSort() {
        for(int k = 1; k <= noOfRects - 1; k++) {
            int flag = 0;
            for (int i = 0; i <= noOfRects - k - 1; i++) {
                if (rectangles[i].getHeight() > rectangles[i + 1].getHeight()) {
                    int temp = rectangles[i].getHeight();
                    rectangles[i].setHeight(rectangles[i + 1].getHeight());
                    rectangles[i + 1].setHeight(temp);
                    flag = 1;

                    paintImmediately(0, 0, 800, 600);

                    // repaint() doesn't work here, and the paintImmediately() function must be called from something
                    // known as Event Dispatching Thread idk.
                }
            }
            if(flag == 0)
                break;
        }

        MyPanel.isRandom = false;
        MyPanel.isNearlySorted = false;
        MyPanel.isReversed = false;
        MyPanel.isFewUnique = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
                bubbleSort();
        }
    }
}
