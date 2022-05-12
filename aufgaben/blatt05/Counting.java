import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Counting {

    // Setze die max als kleinstmöglicher Wert.
    // Für jedes Element, falls es größer als daszeitiges Max ist, wird es das neue Max
    static int getMax(int[] data) {
        int max = Integer.MIN_VALUE;
        for (int i : data) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    // gleiche Konzept wie getMax
    static int getMin(int[] data) {
        int min = Integer.MAX_VALUE;
        for (int i : data) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    static int[] count(int[] data, int min, int max) {
        int[] freq = new int[max - min + 1];
        for (int elm : data) {
            freq[elm - min]++;
        }
        return freq;
    }

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
        int[] zahlen = new int[list.size()];
        for (int i = 0; i < zahlen.length; i++) {
            zahlen[i] = list.get(i);
        }

        System.out.println("The minimum value: " + getMin(zahlen));
        System.out.println("The maximum value: " + getMax(zahlen));
        System.out.println("Frequencies: " + Arrays.toString(count(zahlen, getMin(zahlen), getMax(zahlen))));
    }
}