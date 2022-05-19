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
        lsdRadix(array);
        System.out.println(Arrays.toString(array));

    }
    public static void sortByByte(int[] input, int l, int r, int b) {
        int a = 256;
        int[] count = new int[256];
        int[] hilfsarray = new int[r - l + 1];
        // Count frequencies
        for (int i = 0; i < input.length; i++) {
            int bucketIndex = (input[i] >> (8 * b)) & 0xFF;
            count[bucketIndex]++;
        }
        // Compute cumulates
        for (int i = count.length-2; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        // Move records
        for (int i = 0; i < input.length; i++) {
            hilfsarray[count[(input[i] >> (8 * b)) & 0xFF] - 1] = input[i];
            count[(input[i] >> (8 * b)) & 0xFF]--;
        }
        System.out.println(Arrays.toString(count));

        // Copy back
        for (int i = 0; i < input.length; i++) {
            input[i] = hilfsarray[i];
        }

    }
    public static void lsdRadix(int[] data) {
        for(int i = 0; i < 4; i++){
            sortByByte(data, 0, data.length-1, i);
        }
    }
}
