import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort {

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

        int max, min;

        try {
            max = Counting.getMax(array);
            min = Counting.getMin(array);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array is empty! Min and Max does not exist!");
            return;
        }

        int[] count = Counting.count(array, min, max);
        System.out.println("The minimum value: " + min);
        System.out.println("The maximum value: " + max);
        System.out.println("Before Sorting: " + Arrays.toString(array));
        System.out.println("After Sorting: " + Arrays.toString(countingSort(array)));

        System.out.println("Frequencies: " + Arrays.toString(count));
    }

    public static int[] countingSort(int[] data) {
        // min, max und Frequenzarray generieren
        int max = Counting.getMax(data);
        int min = Counting.getMin(data);
        int[] count = Counting.count(data, min, max);

        // kumulative Summe der Frequenzen in dem selben Array speichern
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // j ist die Frequenzanzahl des count-Arrays.
        int j = 0;
        // length iteriert das Array vom Ende, und notiert, wie weit wir das Array noch gefüllt hat
        int length = data.length - 1;
        // für jeden Frequenz im count-Array ...
        for (int i = 0; i < count.length; i++) {
            // ... falls j kleiner als die Anzahl der Vorkommen jeder Zahl ist,
            // füllen wir vom Ende ab
            while (j < count[i]) {
                data[length] = i + min;
                if (length == 0)
                    break;
                length--;
                j++;
            }
        }
        return data;
    }
}
