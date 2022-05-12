import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Select {
    public static void main(String[] args) {

        // Aufgabe 1.1b

        // Scanner fuer Lesen von Standard-In
        Scanner sc = new Scanner(System.in);

        // ArrayList statt normales Array, da wir die Inputgroesse noch nich wissen
        ArrayList<Integer> eingabe = new ArrayList<>();

        // Lese die Inputs solange es noch \n gibt
        while (sc.hasNextLine()) {
            // die Inputs werden als String interpretiert, deshalb muessen wir zum Integer
            // umwandeln.
            // Das try-Block versucht die Umwandlung und fuegt die Zahl in die Liste ein...
            try {
                int val = Integer.parseInt(sc.nextLine());
                eingabe.add(val);
            }
            // ...und falls die Umwandlung fehlschlaegt, wird ein Exception ausgelöst,
            // welches dann von catch-Block aufgefangen und gemeldet wird.
            catch (NumberFormatException e) {
                System.out.println("Input is not a number!");
                return;
            }
        }

        // Da ArrayList<Integer> kann nich direkt in int[] umgewandelt werden kann,
        // muessen wir die Werte nacheinander umwandeln.
        int[] eingabeInt = new int[eingabe.size()];
        for (int i = 0; i < eingabeInt.length; i++) {
            eingabeInt[i] = eingabe.get(i).intValue();
        }

        // Aufgabe 1.1c

        // Argumente checken: Nur eine Eingabe fuer k ist erlaubt!
        if (args.length != 1) {
            System.out.println("Only one argument is allowed!");
            return;
        }

        // Wie oben: Standardisierung von Eingabe k.
        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input k is not a number!");
            return;
        }

        Arrays.sort(eingabeInt);
        // Das k-te Element existiert nicht, wenn die Liste weniger als k Elemente hat
        if (k > eingabeInt.length) {
            System.out.println(
                    "List must contain k=" + k + " numbers, but only " + eingabeInt.length + " were provided.");
            return;
        } else if (k == 0) {
            System.out.println("0th element does not exist.");
        }
        else if (k < 0) {
            System.out.println(k + "-th element does not exist.");
        }
        // Das k-te kleinste Element hat den Index k-1
        else {
            System.out.println("The " + k + "-smallest element is " + eingabeInt[k - 1]);
        }

    }
}
