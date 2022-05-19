import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

public class MinHeapSort {
    static void minHeapify(int[] arr, int n, int i) {
        int smallest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2;

        // Wenn LinksKinderKnoten kleiner als Wurzel ist
        if (l < n && arr[l] < arr[smallest])
            smallest = l;

        // Wenn RechtsKinderKnoten kleiner als Wurzel ist
        if (r < n && arr[r] < arr[smallest])
            smallest = r;

        // Wenn smallest kein Wurzel ist
        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            // Rekursiv minHeapify aufrufen
            minHeapify(arr, n, smallest);
        }
    }

    static void buildMinHeap(int[] arr, int n) {
        // Min-Heap erstellen
        for (int i = n / 2 - 1; i >= 0; i--)
            minHeapify(arr, n, i);
    }

    public static int extractMin(int[] data, int n) {
        // Der Wurzel mit dem Knoten am Ende tauschen
        int temp = data[0];
        data[0] = data[n - 1];
        data[n - 1] = temp;

        
        // minHeapify auf das reduzierte Heap
        minHeapify(data, n-1, 0);


        return temp;
    }

    public static int heapSelect(int[] data, int k) {
        // Zuerst ein Min-Heap bauen
        buildMinHeap(data,data.length);
        int kth = 0;
        // Für jede Schleife nehmen wir das kleinste Element
        // -> für die k-te Schleife wird das k-kleinste Element ausgewählt
        for(int i = 0; i < k; i++) {
            kth = extractMin(data, data.length-i);
        }
        return kth;
    }
    
    public static int assertion(int[] data, int k){
        Arrays.sort(data);
        return data[k-1];
    }

    public static void main(String[] args) {
        
        // Eingabe lesen und checken
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            try {
                int val = Integer.parseInt(sc.nextLine());
                list.add(val);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number!");
                return;
            }
        }

        // ArrayList in int[] umwandeln
        int kth = 0;
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Input k is not a number!");
            return;
        }

        if (k <= 0) {
            System.out.println("Negative or 0-th element does not exist! Please start from 1.");
            return;
        }

        if (k > array.length) {
            System.out.println("k is too big!");
            return;
        }
        
        
        // array1 für Laufzeitsmessung und array2 zum Prüfen von Assertion
        int[] array1 = array.clone();
        int[] array2 = array.clone();
        
        Instant start1 = Instant.now();
        kth = heapSelect(array1, k);
        Instant finish1 = Instant.now();
        long time1 = Duration.between(start1, finish1).toMillis();

        System.out.println(k + "th-smallest element is " + kth);

        System.out.println("Time: " + time1);
        assert assertion(array2,k) == kth : "Es gibt einen Fehler";

    }

}
