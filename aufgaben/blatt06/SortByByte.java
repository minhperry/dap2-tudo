import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class SortByByte {
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

        System.out.println("Before sorted: " + Arrays.toString(array));
        System.out.println("Each number can also be represented as a vector of length 4:");
        extractBytesFromArray(array);
        lsdRadix(array);
        System.out.println("After Sorted: " + Arrays.toString(array));

        assert (isSorted(array)) : "Array was not sorted correctly!";

    }

    // Hilfsmethode für Illustration
    public static void extractBytesFromArray(int[] array) {
        int[][] bytes = new int[array.length][4];
        for (int i = 0; i < array.length; i++) {
            int k = 0;
            for (int j = 3; j >= 0; j--) {

                bytes[i][j] = extractByte(array[i], k++);
            }
            System.out.println(array[i] + " = " + Arrays.toString(bytes[i]));
        }
    }

    // Hilfsmethode für Assertion
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i - 1] < array[i])
                return false;
        return true;
    }

    // Hilfsmethode die das b-te Byte von num zurückgeben.
    public static int extractByte(int num, int b) {
        return (num >> (8 * b)) & 0xFF;
    }

    // Gleiches Konzept wie Counting Sort, mit min = 0 und max = 255
    public static void sortByByte(int[] input, int l, int r, int b) {
        int[] count = new int[256];
        int[] hilfsarray = new int[r - l + 1];

        // Frequenz zählen
        for (int i = 0; i < input.length; i++) {
            count[extractByte(input[i], b)]++;
        }

        // Kummulative Summe berechnen
        for (int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        // Setze die Zahlen in die richtige Position im Hilfsarray
        for (int i = 0; i < input.length; i++) {
            hilfsarray[--count[extractByte(input[i], b)]] = input[i];
            // count[extractByte(input[i], b)]--;
        }
        System.out.println(Arrays.toString(count));

        // Am Ende Hilfsarray im Originalarray zurück kopieren
        for (int i = 0; i < input.length; i++) {
            input[i] = hilfsarray[i];
        }

    }

    public static void lsdRadix(int[] data) {
        for (int i = 0; i < 4; i++) {
            sortByByte(data, 0, data.length - 1, i);
        }
    }
}
