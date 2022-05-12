import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class EnumeratePermutations {

    // Methode zum Vertauschen von 2 Elementen eines Arrays
    // Man kann nicht direkt tauschen (a := b dann b := a),
    // da ein Wert davon geloescht wird.
    // (a := b loescht den Originalwert von a, dann wird b := a gleich wie b := b)
    // Deshalb braucht man eine temporaere Variable, um den Originalwert zu
    // speichern
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    // Speicherung der Anzahl der Permutationen
    static int rechenschritte = 0;

    public static int printPermutations(int[] array, int d) {
        if (array.length == 0)
// print []
            return 1;
        // Die Elementen vom d-ten bis zum letzten Index wurde noch nicht permutiert
        // Starte mit dem ersten Element (d = 0 unten) und tausche mit jedem Element
        // im Array. Das erste Element wird nun als "permutiert" betrachtet.
        // Die Rekursion printPermutations(array, d + 1) permutiert dann das nicht
        // permutierte Teil (d+1 -> Ende) des Arrays. Die zweite Tauschung bring die
        // Originalordnung zurueck, sonst gibt es wiederholte Werte
        for (int i = d; i < array.length; i++) {
            swap(array, i, d);
            printPermutations(array, d + 1);
            swap(array, d, i);
        }
        // Wenn d gleich das letzte Index ist, d.h. wir haben eine vollstaendige
        // Permutation, erhoehen wir die Permutationsanzahl um 1 und gibt dann diese
        // Permutation aus. Die Permutationsanzahl wird dann zurueckgeben.
        if (d == array.length - 1) {
            rechenschritte++;
            System.out.println(Arrays.toString(array));
        }
        return rechenschritte;
    }

    public static void main(String[] args) {

        // Alles wie 1.1b
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
        int[] ints = new int[eingabe.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = eingabe.get(i).intValue();
        }
        // Ende der Importierung

        System.out.println("Es gibt " + printPermutations(ints, 0) + " Permutation(en) der Eingabe.");

    }
}
