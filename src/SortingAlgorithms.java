import javax.swing.JComboBox;

public interface SortingAlgorithms {

    static String[] sortsArray = {"Bubble Sort", "Selection Sort","Insertion Sort","Merge Sort","Quick Sort","Heap Sort"};
    static JComboBox<String> sortsComboBox = new JComboBox<>(sortsArray);

    void bubbleSort();
    void selectionSort();
    void insertionSort();
    void mergeSort();
    void quickSort();
    void heapSort();
}
