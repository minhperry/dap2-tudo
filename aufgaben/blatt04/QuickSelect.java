import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class QuickSelect {
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

        // ArrayList -> int[]
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input k is not a number!");
            return;
        }

        String wort = args[1];

        int[] array1 = array.clone();
        int[] array2 = array.clone();
        int[] array3 = array.clone();
        int[] array4 = array.clone();
        int[] array5 = array.clone();
        
       
            if (wort.equals("quickr")) {          
                Instant start1 = Instant.now();
                int kth1 = quickSelectRand(array1, 0, array1.length-1, k-1);
                Instant finish1 = Instant.now();
                long time1 = Duration.between(start1, finish1).toMillis();
                
                System.out.println(k + "th-smallest element is " + kth1);

                System.out.println("TimeQuickr: " + time1);
                assert assertion(array2,k) == kth1 : "Es gibt einen Fehler";

            } else if(wort.equals("quickf")){                
                Instant start2 = Instant.now();
                int kth2 = quickSelectFirst(array3, 0, array1.length-1, k-1);
                Instant finish2 = Instant.now();
                long time2 = Duration.between(start2, finish2).toMillis();

                System.out.println(k + "th-smallest element is " + kth2);
                
                System.out.println("TimeQuickf: " + time2);
                assert assertion(array4,k) == kth2 : "Es gibt einen Fehler";

            }
            else if (wort.equals("heap")) {
                Instant start3 = Instant.now();
                int kth3 = heapSelect(array5, k);
                Instant finish3 = Instant.now();
                long time3 = Duration.between(start3, finish3).toMillis();

                System.out.println(k + "th-smallest element is " + kth3);

                System.out.println("TimeHeap: " + time3);
                assert assertion(array5,k) == kth3 : "Es gibt einen Fehler";
            } else {
                System.out.println("Input string does not match anything.");
            }
    }
    
    // Hilfsmethode zum Wählen von einer zufälligen Nummer zwischen min und max
    public static int rand(int min, int max)
    {
        // min und max checken: min soll kleiner als max, oder die Zahlenbereich soll nicht die int-Grenze überschreiten
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    // Hilfsmethode zum Tauschen
    public static void swap(int[] data, int i, int j)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public static int partition(int[] data, int l, int p, int r)
    {
        // Pivot als das gewählte Index
        int pivot = data[p];

        // Pivot zum Ende bringen
        swap(data, p, r);
       
        // ?
        p = l;

        // Das Array von links nach rechts iterieren. 
        // kleinere oder gleiche Elemente soll links des Pivots, und größere Elemente rechts des Pivots verschoben
        // wie Blatt2 Partitionierung
        for (int i = l; i < r; i++)
        {
            if (data[i] <= pivot)
            {
                swap(data, i, p);
                p++;
            }
        }

        // Pivot zur richtigen Position bringen und gibt die Position zurück
        swap(data, p, r);
        return p;
    }

    public static int quickSelectFirst(int[] data, int l, int r, int k)
    {
        // Falls das Array nur ein Element hat, gibt dieses Element dann zurück
        if (l == r) {
            return data[l];
        }

        // Pivot p = l wählen
        // Nach die Partionierung steht das Pivot an der richtigen Position
        int p = l;
        p = partition(data, l, p, r);

        // Falls diese die gesuchte Position ist dann geben wir die p- bzw. k-kleinste Element zurück
        if (k == p) {
            return data[k];
        }

        // Falls k rechts des Pivots steht dann wählen wir aus der linken Seite des Pivots ...
        else if (k < p) {
            return quickSelectFirst(data, l, p - 1, k);
        }

        // ... sonst rechten Seite
        else {
            return quickSelectFirst(data, p + 1, r, k);
        }
    }

    public static int quickSelectRand(int[] data, int l, int r, int k)
    {
        // Wie oben
        if (l == r) {
            return data[l];
        }

        // Statt ein bestimmtes Pivot wählen wir ein zufälliges
        int p = rand(l, r);
        p = partition(data, l, p, r);

        if (k == p) {
            return data[k];
        }
        else if (k < p) {
            return quickSelectRand(data, l, p - 1, k);
        }
        else {
            return quickSelectRand(data, p + 1, r, k);
        }
    }

    // Start Min-Heap-Methode
    static void minHeapify(int arr[], int n, int i) {
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

            // Recursiv minHeapify aufrufen
            minHeapify(arr, n, smallest);
        }
    }

    static void buildMinHeap(int arr[], int n) {
        // Min-Heap erstellen
        for (int i = n / 2 - 1; i >= 0; i--)
            minHeapify(arr, n, i);
    }

    public static int extractMin(int[] data, int n) {
        // Der Wurzel zum Ende bringen
        int temp = data[0];
        data[0] = data[n - 1];
        data[n - 1] = temp;

        
        // minHeapify auf das reduzierte Heap
        minHeapify(data, n-1, 0);


        return temp;
    }

    public static int heapSelect(int [] data, int k) {
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
    // End Min-Heap-Methode


    public static int assertion(int[] data, int k){
        Arrays.sort(data);
        return data[k-1];
    }
}
