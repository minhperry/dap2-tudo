import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LexPermute {

    // Hilfsmethode, die das Element mit Index idx entfernt, und das Array dann verkleinert.
    public static int[] removeAndShrink(int[] array, int idx) {
        // Kopie von Array mit 1 weniger Element
        int[] copy = new int[array.length - 1];
        // Für jede Index i im Array, falls es nicht das gesuchte Index ist dann einfach 
        // die Elemente darüber kopieren. Sonst überspringen wir das gesuchte Index, und
        // Zeiger j ist jetzt immer 1 Element langsamer als Zeiger i. 
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != idx) {
                copy[j++] = array[i];
            }
        }
        // Das Endergebnis mit entfernten Element wird dann zurückgegeben.
        return copy;
    }

    public static int[] getPermutation(int[] data, int k) {
        int n = data.length;
        // Arrays.sort(data);
        // im main() wird das Array schon sortiert

        // Wir nutzen das 0-Indexing, deshalb hat die k-kleinste Permutation 
        // das Index k - 1
        k = k - 1;

        // Berechne fact = (n-1)! für spätere Nutzungen wie im Tipp
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact = fact * i;
        }

        // k > n! -> k-te Perm. exisitiert nicht
        if (k + 1 > fact * n) {
            throw new NumberFormatException("k is bigger than the amount of permutations!");
        }

        // arr speichert die gesuchte Permutation und soll deshalb die gleiche Länge wie data haben
        // j ist dafür der Zeiger, der angibt, wie weit das Array gefüllt ist
        int[] arr = new int[n];
        int j = 0;
        while (true) {
            // "Die (k = 1 + i * fact)-kleinste Perm. beginnt mit a_i"
            // -> i = floor((k - 1)/fact). Bei positive Nummer ist int = int/int gleich wie floor()
            // -> (int) i = (int) (k - 1) / (int) fact
            // z.B. 8 / 3 = 2.(6). 
            // floor() nimmt die nächste kleinere ganze Zahl = 2
            // int = int / int entfernt einfach den Dezimalteil = 2
            // Die Zahl a_i wird in dem Array gespeichert...
            arr[j++] = data[k / fact];
            // ... und dann von dem Originalarray entfernt an den i-te Index.
            data = removeAndShrink(data, k / fact);

            // Haltebedingung: wenn das ursprüngliche Array keine Elemente mehr hat, 
            // können wir nichts nehmen
            if (data.length == 0) {
                break;
            }
            // Die Bedingung müssen hier stehen, und kann weder die while-Bedingung noch 
            // am Ende jeder Schleife sein, sonst bei der letzten Extraktion / letzten
            // Aufrufe von removeAndShrink() ist data-Array leer aka data.length = 0, 
            // 1. bei der Verkleinerungsdivision passiert dann einen "geteilt durch 0" Fehler
            // 2. leeres Array kann nicht mehr verkleinert werden

            // Verkleinerung des Problems für die restlichen ELemente
            k = k % fact;
            fact = fact / data.length; // (n-1)! -> (n-2)! -> (n-3)! -> ...
        }
        return arr;
    }

    public static void main(String[] args) {

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

        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input k is not a number!");
            return;
        }

        // Zum Debuggen
        /*
        int[] zahlen = { 1, 2, 3, 4 };
        int k = 24;
        */

        if (k < 0) {
            System.out.println("Negative permutation does not exist!");
            return;
        }

        if (k == 0) {
            System.out.println("0th permutation does not exist! Please start from 1st!");
            return;
        }

        Arrays.sort(zahlen);
        System.out.println("Sorted output:\n" + Arrays.toString(zahlen));
        try {
            System.out.println("The " + k + "-smallest permutation is:\n"
                    + Arrays.toString(getPermutation(zahlen, k)));
        } catch (NumberFormatException e) {
            System.out.println("k is bigger than the amount of permutations!");
        }
    }
}
