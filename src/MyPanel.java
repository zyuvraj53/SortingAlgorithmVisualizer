import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyPanel extends JPanel implements SortingAlgorithms, ActionListener {

    final static int SCREEN_WIDTH = 600;
    final static int SCREEN_HEIGHT = 600;
    final static int DELAY = 50;
    static int noOfRects;
    Rectangle[] rectangles;

    JButton playButton;

    static {
        noOfRects = 50;
    }

    MyPanel() throws InterruptedException {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //this.setBounds(200, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setBackground(new Color(125, 50, 255));
        rectangles = new Rectangle[noOfRects];

        // setting a random height for all the rectangles
        Random random = new Random();
        for(int i = 0; i < noOfRects; i++){
            rectangles[i] = new Rectangle(random.nextInt(0, SCREEN_HEIGHT));
            System.out.println(rectangles[i].getHeight());
        }
        Rectangle.width = SCREEN_WIDTH / noOfRects;

        playButton = new JButton();
        ImageIcon playIcon = new ImageIcon("C:\\Users\\Yuvraj\\Desktop\\Java\\SortingAlgorithmsVisualizer\\src\\playButton.png"); // load the image to a imageIcon
        Image playImage = playIcon.getImage(); // transform it
        Image newPlayImg = playImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        playIcon = new ImageIcon(newPlayImg);  // transform it back
        playButton.setIcon(playIcon);
        this.add(playButton);
        playButton.addActionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        for(int i = 0; i < noOfRects; i++){
            g2D.setColor(new Color(50, 50, 50));
            g2D.fillRect(i * Rectangle.width, SCREEN_HEIGHT - rectangles[i].getHeight(), Rectangle.width, rectangles[i].getHeight());
            g2D.setColor(new Color(10, 10, 10));
            g2D.drawRect(i * Rectangle.width, SCREEN_HEIGHT - rectangles[i].getHeight(), Rectangle.width, rectangles[i].getHeight());
        }
    }

    @Override
    public void bubbleSort() throws InterruptedException {
        for(int k = 1; k <= noOfRects - 1; k++) {
            int flag = 0;
            for (int i = 0; i <= noOfRects - k - 1; i++) {
                if (rectangles[i].getHeight() > rectangles[i + 1].getHeight()) {
                    int temp = rectangles[i].getHeight();
                    rectangles[i].setHeight(rectangles[i + 1].getHeight());
                    rectangles[i + 1].setHeight(temp);
                    //Thread.sleep(10);
                    flag = 1;
                    //paintImmediately(200, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                    paintImmediately(this.getBounds());
                    // repaint() doesn't work here, and the paintImmediately() function must be called from something
                    // known as Event Dispatching Thread idk.
                }
            }
            if(flag == 0)
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            try {
                bubbleSort();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
