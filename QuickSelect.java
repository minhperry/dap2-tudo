import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class QuickSelect {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = Integer.parseInt(args[0]);
        String wort = args[1];
        ArrayList<Integer> list = new ArrayList<Integer>();
        String line = "";
        while (input.hasNextLine()) {
            try {
                line = input.nextLine();
                if (line.equals("")) break;
                int value = Integer.parseInt(line);
                list.add(value);
            } catch (NumberFormatException e) {
                System.err.println("Invalid Input");
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        int[] array1 = array;
        int[] array2 = array;
        int[] array3 = array;
        int[] array4 = array;
        if (wort == "heap" || wort == "quickr" || wort == "quickf") {
            if (wort == "quickr") {
                Instant start1 = Instant.now();
                int kth1 = quickSelectRand(array1, 0, array1.length-1, k-1);
                Instant finish1 = Instant.now();
                long time1 = Duration.between(start1, finish1).toMillis();
                System.out.println("TimeQuickr: " + time1);
                assert assertion(array2,k) == kth1 : "Es gibt einen Fehler";
            } else if(wort == "quickf"){
                Instant start2 = Instant.now();
                int kth2 = quickSelectFirst(array3, 0, array1.length-1, k-1);
                Instant finish2 = Instant.now();
                long time2 = Duration.between(start2, finish2).toMillis();
                System.out.println("TimeQuickf: " + time2);
                assert assertion(array4,k) == kth2 : "Es gibt einen Fehler";
            }
        }
    }
    public static int rand(int min, int max)
    {
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    public static void swap(int[] data, int i, int j)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // Partition using Lomuto partition scheme
    public static int partition(int[] data, int l, int p, int r)
    {
        // pick `pIndex` as a pivot from the array
        int pivot = data[p];

        // Move pivot to end
        swap(data, p, r);

        // elements less than the pivot will be pushed to the left of `pIndex`;
        // elements more than the pivot will be pushed to the right of `pIndex`;
        // equal elements can go either way
        p = l;

        // each time we find an element less than or equal to the pivot, `pIndex`
        // is incremented, and that element would be placed before the pivot.
        for (int i = l; i < r; i++)
        {
            if (data[i] <= pivot)
            {
                swap(data, i, p);
                p++;
            }
        }

        // move pivot to its final place
        swap(data, p, r);

        // return `pIndex` (index of the pivot element)
        return p;
    }

    public static int quickSelectFirst(int[] data, int l, int r, int k)
    {
        // If the array contains only one element, return that element
        if (l == r) {
            return data[l];
        }

        // select a `pIndex` between left and right
        int p = data[l];

        p = partition(data, l, p, r);

        // The pivot is in its final sorted position
        if (k == p) {
            return data[k];
        }

        // if `k` is less than the pivot index
        else if (k < p) {
            return quickSelectFirst(data, l, p - 1, k);
        }

        // if `k` is more than the pivot index
        else {
            return quickSelectFirst(data, p + 1, r, k);
        }
    }

    public static int quickSelectRand(int[] data, int l, int r, int k)
    {
        // If the array contains only one element, return that element
        if (l == r) {
            return data[l];
        }

        // select a `pIndex` between left and right
        int p = rand(l, r);

        p = partition(data, l, p, r);

        // The pivot is in its final sorted position
        if (k == p) {
            return data[k];
        }

        // if `k` is less than the pivot index
        else if (k < p) {
            return quickSelectRand(data, l, p - 1, k);
        }

        // if `k` is more than the pivot index
        else {
            return quickSelectRand(data, p + 1, r, k);
        }
    }

    public static int assertion(int[] data, int k){
        Arrays.sort(data);
        return data[k-1];
    }
}
