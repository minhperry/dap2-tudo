import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

class LSDRadixSort {

	public static void main(String[] args) {

		// Eingabe lesen und checken
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		while (sc.hasNextLine()) {
			try {
				int val = Integer.parseInt(sc.nextLine());
                if (val < 0) {
                    System.out.println("Negative inputs are not allowed!");
                    return;
                }
				list.add(val);
			} catch (NumberFormatException e) {
				System.out.println("Input is not a number, or is out of int limit!");
				return;
			}
		}

		// ArrayList in int[] umwandeln
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}

		// System.out.println("Each number can also be represented as a vector of length 4:");
		// extractBytesFromArray(array);
		System.out.println("Before sorted: " + Arrays.toString(array));
        Instant start = Instant.now();
		lsdRadix(array);
        Instant end = Instant.now();
		System.out.println("After Sorted: " + Arrays.toString(array));

        System.out.println("Time needed: " + Duration.between(start, end).toNanos() + "ns.");

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

	// Sortiert jede Stelle
	public static void lsdRadix(int[] data) {
		for (int i = 0; i < 4; i++) {
			sortByByte(data, 0, data.length - 1, i);
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

	// (fast) gleiches Konzept wie Counting Sort 
	public static void sortByByte(int[] input, int l, int r, int b) {
		int[] help = new int[r - l + 1];
		int[] count = new int[256];

		// Frequenzen zählen und kummulative Summe berechnen
		for (int i = l; i <= r; i++) {
			count[extractByte(input[i], b)]++;
		}
		for (int i = count.length - 1; i > 0; i--) {
			count[i - 1] += count[i];
		}

		// Zeiger j iteriert das Array nach vorne (l <- r)
		// Konzept wie Counting Sort: Lese das Byte, suche Frequenz in count-Array,
        // speichert der Wer ins Hilfsarray
		int j = r;
		while (j >= l) {
			int digit = extractByte(input[j], b);
			help[--count[digit]] = input[j];
			j--;
		}

		// Hilfsarray drüber kopieren
		for (int i = l; i <= r; i++) {
			input[i] = help[i];
		}
	}
}
