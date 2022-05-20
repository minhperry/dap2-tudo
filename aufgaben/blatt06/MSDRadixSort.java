import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import static java.lang.Math.*;

class MSDRadixSort {

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

		//ArrayList in int[] umwandeln
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}

		System.out.println("Before sorted: " + Arrays.toString(array));
        Instant start = Instant.now();
		msdRadix(array);
        Instant end = Instant.now();
		System.out.println("After Sorted: " + Arrays.toString(array));

        System.out.println("Time needed: " + Duration.between(start, end).toNanos() + "ns.");

        assert (LSDRadixSort.isSorted(array)) : "Array was not sorted correctly!";
	}

	// Insertion-Sort für kürze Länge
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] < key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	// Fängt von dem ersten Byte an
	public static void msdRadix(int[] data) {
		msdRadix(data, 0, data.length - 1, 3);
	}

	public static void msdRadix(int[] data, int l, int r, int b) {

		// Für kürze Länge nutzen wir Insertion Sort wie definiert
		if (r - l + 1 <= 32) {
			insertionSort(data);
		} else if (b < 0) {
            return;
        } else {
			int[] C = new int[257];

			// wie LSD : Frequenz zählen
			for (int i = l; i <= r; i++) {
				int digit = LSDRadixSort.extractByte(data[i], b);
				C[digit]++;
			}

			C[C.length - 2]--;

			// wie LSD: kummulative Summe 
			for (int i = C.length - 2; i > 0; i--) {
				C[i - 1] += C[i];
			}

			// dann sortieren wir dieses Byte...
			LSDRadixSort.sortByByte(data, l, r, b);

			// ..und rekursiv Sortieren für nächstes Byte
			for (int i = 0; i < C.length - 1; i++) {
				// msdRadix(data, C[i + 1] + 1, C[i], b - 1);
                msdRadix(data, C[i] + 1, C[i + 1] - 1, b - 1);
			}
		}

	}

}
