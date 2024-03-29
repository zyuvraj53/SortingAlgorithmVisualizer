import javax.swing.JComboBox;

public interface SortingAlgorithms {

    static String[] sortsArray = {"Bubble Sort", "Selection Sort"};
    static JComboBox<String> sortsComboBox = new JComboBox<>(sortsArray);

    void bubbleSort();
    void selectionSort();
}
