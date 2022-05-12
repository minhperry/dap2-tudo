import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ExactSelect {
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

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Real " + k + "-smallest element is " + exactSelect(array, k));

    }

    // Hilfsmethode, die checken, ob eine Zahl im data-Array vorkommt.
    // Konzept wie count Methode
    static boolean[] isInArray(int[] data, int min, int max) {
        boolean[] isIn = new boolean[max - min + 1];
        for (int i : data) {
            isIn[i - min] = true;
        }
        return isIn;
    }

    public static int exactSelect(int[] data, int k) {
        // Da wir die Hilfsmethode braucht, soll Min und Max determiniert werden, dazu auch die Ausnahmebehandlung.
        int min, max, toReturn = Integer.MAX_VALUE;
        try {
            max = Counting.getMax(data);
            min = Counting.getMin(data);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array is empty! Min and Max does not exist!");
            return toReturn;
        }

        boolean[] isIn = isInArray(data, min, max);

        // Illustration der Algo
        System.out.print("Illustration: [");
        for (int i = 0; i < isIn.length; i++) {
            System.out.print((i + min) + "=" + isIn[i]);
            if (i != isIn.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");

        // Für jeden gefundenen Wert (aka mit true markiert), wurde 1 von k abgezogen.
        // Wenn k = 0 bedeutet es dass das derzeitige Index mit min-Offset der gesuchte Wert ist.
        for (int i = 0; i < isIn.length; i++) {
            if (isIn[i] == true) {
                k--;
            }
            if (k == 0) {
                toReturn = i + min;
                break;
            }
        }
        return toReturn;
    }
}
