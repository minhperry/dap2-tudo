import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Counting {
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

        // leeres Array fangen und das Programm dann enden
        int min, max;
        try {
            max = getMax(array);
            min = getMin(array);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array is empty! Min and Max does not exist!");
            return;
        }

        // sonst wenn das Array nicht leer ist, Min, Max und Frequezen ausdrücken
        int[] count = count(array, min, max);
        System.out.println("The minimum value: " + min);
        System.out.println("The maximum value: " + max);
        System.out.println("Frequencies: " + Arrays.toString(count));
    }

    public static int getMin(int[] data) {
        // Falls das Array Länge 0 hat, existiert kein Minimum
        if (data.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Array is empty! Cannot find smallest element!");
        } else {
            // Setze die min als erstes Element des Arrays
            // Für jedes Element, falls es kleiner als daszeitiges Min ist, wird es das neue Min
            int min = data[0];
            for (int i : data) {
                if (i < min) {
                    min = i;
                }
            }
            return min;
        }
    }

    // gleiches Konzept wie getMin()
    public static int getMax(int[] data) {
        if (data.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Array is empty! Cannot find largest element!");
        } else {
            int max = data[0];
            for (int i : data) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        }
    }

    // Index i - min von Array freq speichert den Frequenz eines Elements
    // z.B. min = k -> Frequenz von k wird im Index k - k = 0 gespeichert, k+1 im Index k+1 - k = 1, etc.
    public static int[] count(int[] data, int min, int max) {
        int[] freq = new int[max - min + 1];
        for (int i : data) {
            freq[i - min]++;
        }
        return freq;
    }
}
