import java.util.Arrays;

public class ByteSort {

    // Hilfsmethode, die das b-te Byte von Zahl num zurückgibt.
    public static int extractByte(int num, int b) {
        return (num >> (8 * b)) & 0xFF;
    }

    public static void sortByByte(int[] input, int l, int r, int b) {

        // Hilfsarray zum Speichern von b-te Byte zwischen [l, r]
        int[] help = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            help[i - l] = extractByte(input[i - l], b);
        }

        System.out.println(Arrays.toString(help));

        // Frequenzarray generieren, gleiches Konzept wie Blatt 5 count() mit min = 0, max = 255
        int[] freq = new int[256];
        for (int i : help) {
            freq[i]++;
        }

        // kumulative Summe der Frequenzen in dem selben Array speichern
        // ab hier gleiches Konzept wie Blatt 5
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        int j = 0;
        int k = input.length - 1;
        for (int i = 0; i < freq.length; i++) {
            while (j < freq[i]) {
                input[k] = i;
                if (k == 0)
                    break;
                k--;
                j++;
            }
        }

        System.out.println(Arrays.toString(input));
    }

    public static void main(String[] args) {
        int[] nums = { 811153, 134615049, 1541968147, 1011442876, 1661914940 };
        sortByByte(nums, 0, nums.length - 1, 0);
    }
}
