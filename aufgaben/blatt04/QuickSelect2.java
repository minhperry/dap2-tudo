import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class QuickSelect {
    public static void main(String[] args) {
        
        // Eingabe lesen und checken
        Scanner input = new Scanner(System.in);
        int k = Integer.parseInt(args[0]);
        String wort = args[1];
        ArrayList<Integer> list = new ArrayList<Integer>();
        String line = "";
        while (input.hasNextLine()) {
            try {
                line = input.nextLine();
                if (line.equals("")) break;
                int value = Integer.parseInt(line);
                list.add(value);
            } catch (NumberFormatException e) {
                System.err.println("Invalid Input");
            }
        }
        
        // ArrayList -> int[]
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        int[] array1 = array.clone();
        int[] array2 = array.clone();
        int[] array3 = array.clone();
        int[] array4 = array.clone();
        
        if (wort == "heap" || wort == "quickr" || wort == "quickf") {
            if (wort == "quickr") {
                
                Instant start1 = Instant.now();
                int kth1 = quickSelectRand(array1, 0, array1.length-1, k-1);
                Instant finish1 = Instant.now();
                long time1 = Duration.between(start1, finish1).toMillis();
                
                System.out.println("TimeQuickr: " + time1);
                assert assertion(array2,k) == kth1 : "Es gibt einen Fehler";
            } else if(wort == "quickf"){
                
                Instant start2 = Instant.now();
                int kth2 = quickSelectFirst(array3, 0, array1.length-1, k-1);
                Instant finish2 = Instant.now();
                long time2 = Duration.between(start2, finish2).toMillis();
                
                System.out.println("TimeQuickf: " + time2);
                assert assertion(array4,k) == kth2 : "Es gibt einen Fehler";
            }
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
        int p = data[l];
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

    public static int assertion(int[] data, int k){
        Arrays.sort(data);
        return data[k-1];
    }
}
