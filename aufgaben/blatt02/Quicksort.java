import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class Quicksort {

    // a

    // Hilfsmethode zum Tauschen von Elementen im Array.
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static int partition(int[] data, int l, int r) {
        // Pivot als erstes Element
        int pivot = data[l];
        // Ein Zeiger der das Array von rechts iterieren,
        // und speichert die richtige Position des Pivots.
        int m = r + 1;
        // Wir gehen von rechts nach links...
        for (int i = r; i > l; i--) {
            // ... und falls ein Element kleiner als das Pivot ist, ...
            if (data[i] < pivot) {
                // ... steigt m um 1 ab, d.h. ein Element schon "partioniert" ist.
                m--;
                // Durch den Tauschungen werden alle kleineren Elemente
                // auf die rechte Seite verschoben.
                swap(data, m, i);

            }
        }
        // Der letzte Tausch bringt das Pivot in die richtige Position,
        // und gibt die Position zurück.
        swap(data, --m, l);
        return m;
    }

    // b

    public static void qsort(int[] data, int l, int r) {
        // Ein Subarray mit l > r würde sinnlos.
        // Deshalb ist die Bedingung l < r ein Muss
        // l = r bedeutet dass es nur ein Element zum Sortieren gibt
        if (l < r) {
            // bring das Pivot in die richtige Position
            int pivot = partition(data, l, r);

            // und bringt derren Pivots in ihre richtige Position für rechte und linke Seite
            qsort(data, l, pivot - 1);
            qsort(data, pivot + 1, r);
        }
    }

    // c

    public static void qsort(int[] data) {
        qsort(data, 0, data.length - 1);
    }

    // 2.3
    public static boolean isSorted(int[] data) {
        int sorted = 1; // 1 = true, 0 = false
        for (int i = 0; i < data.length - 1; i++) {
            // Wenn es nur ein Element gibt, welches folgende Element größer ist,
            // bedeutet es dass das Array unsortiert ist. Sonst ist die if-Bedingung
            // immer falsch und die sorted Variable immer noch 1, d.h. das Array is sortiert.
            if (data[i] < data[i + 1]) {
                sorted *= 0;
            }
        }
        return (sorted == 1) ? true : false;
    }

    // d

    public static void main(String[] args) {

        // Eingabe lesen und ins Integer umwandeln
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> eingabe = new ArrayList<>();
        while (sc.hasNextLine()) {
            try {
                int val = Integer.parseInt(sc.nextLine());
                eingabe.add(val);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number!");
                return;
            }
        }
        int[] zahlen = new int[eingabe.size()];
        for (int i = 0; i < zahlen.length; i++) {
            zahlen[i] = eingabe.get(i).intValue();
        }

        assert (!isSorted(zahlen)) : "Array was not sorted correctly";

        int laenge = zahlen.length;

        Instant start = Instant.now();
        if (laenge < 20 && laenge > 0) {
            System.out.println(Arrays.toString(zahlen));
            qsort(zahlen);
            System.out.println(Arrays.toString(zahlen));
        } else if (laenge <= 0) {
            // leeres Array kann nicht sortiert werden
            System.out.println("There are no numbers to sort!");
            return;
        } else {
            qsort(zahlen);
        }
        Instant end = Instant.now();

        int max = zahlen[0];
        int min = zahlen[laenge - 1];
        double median = 0;

        // Gerade Länge -> Median = Durchschnitt von 2 Zahlen in der Mitte
        // Ungerade Länge -> Median = Die Zahl in der Mitte
        if (laenge % 2 == 0) {
            median = (zahlen[laenge / 2] + zahlen[laenge / 2 - 1]) / 2.0;
        } else {
            median = zahlen[laenge / 2] * 1.0;
        }

        System.out.println("Min: " + min + ", Med: " + median + ", Max: " + max);
        System.out.println("Time: " + Duration.between(start, end).toMillis());
    }

}
