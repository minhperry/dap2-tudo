import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class Quicksort2 {
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static int[] partition(int[] data, int l, int r) {
        // falls lPivot < rPivot dann tauschen wir diese,
        // da wir die Zahlen absteigend sortieren möchten.
        if (data[l] < data[r]) {
            swap(data, l, r);
        }
        // Pivot als erstes (lP) und letztes (rP) Element
        int lP = data[l], rP = data[r];
        // 2 Zeiger die das Array von beide Seiten iterieren,
        // und speichert die richtige Position des Pivots.
        int rZ = r - 1, lZ = l + 1;
        // 3. Zeiger i zum Iterieren durch das Array
        // i ist einfach nur die linksseitige Zeiger wie lZ, 
        // aber lZ speichert noch das notwendige Index
        int i = l + 1;
        // läuft bis die rechts- und linksseitige Zeiger sich überschreiten
        // in jede Schleife werden Zahlen, die größer als die linksseitige Pivot sind,
        // zur linken Seite gebracht, und Zahlen, die kleiner als die rechtsseitige 
        // Pivot sind, zur rechten Seite gebracht. Zahlen, deren Werte zwische die Pivots 
        // liegt, erfüllt die Bedingugen nicht und bleibt einfach da.
        while (i <= rZ) {
            if (data[i] > lP) {
                swap(data, i++, lZ++);
            } else if (data[i] < rP) {
                swap(data, i, rZ--);
            } else {
                i++;
            }
        }
        // Die letzten Tausche bringt die Pivots in ihre richtige Position,
        // und gibt die Positionen zurück.
        swap(data, l, --lZ);
        swap(data, r, ++rZ);
        return new int[] { lZ, rZ };
    }

    public static void qsort(int[] data, int l, int r) {
        // Ein Subarray mit l > r würde sinnlos.
        // Deshalb ist die Bedingung l < r ein Muss
        // l = r bedeutet dass es nur ein Element zum Sortieren gibt
        if (l < r) {
            // bring die Pivots in die richtige Position
            int[] pivot = partition(data, l, r);

            // und bringt derren Pivots in ihre richtige Position für jedes Teil
            qsort(data, l, pivot[0] - 1);
            qsort(data, pivot[0] + 1, pivot[1] - 1);
            qsort(data, pivot[1] + 1, r);
        }
    }

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
