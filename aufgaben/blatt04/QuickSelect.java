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
                int kth1 = quickSelectRand(array1, 0, array1.length-1, k);
                Instant finish1 = Instant.now();
                long time1 = Duration.between(start1, finish1).toMillis();
                System.out.println("TimeQuickr: " + time1);
                assert assertion(array2,k) == kth1 : "Es gibt einen Fehler";
            } else if(wort == "quickf"){
                Instant start2 = Instant.now();
                int kth2 = quickSelectFirst(array3, 0, array1.length-1, k);
                Instant finish2 = Instant.now();
                long time2 = Duration.between(start2, finish2).toMillis();
                System.out.println("TimeQuickf: " + time2);
                assert assertion(array4,k) == kth2 : "Es gibt einen Fehler";
            }
        }
    }
    public static int partition(int [] data, int l, int p, int r){
        int pivot=p;
        int i=(r+1);
        for(int j=r; j>l; j--) {
            if(pivot>=data[j]) {
                i--;
                swap(data, i, j);
            }
        }
        swap(data, i-1, l);
        return i-1;
    }
    public static void swap(int[] data, int a, int b) {
        int temp=data[a];
        data[a]=data[b];
        data[b]=temp;
    }
    public static int quickSelectFirst(int [] data, int l, int r, int k){
        // find the partition
        int partition = partition(data, l, l, r);

        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return data[partition];

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k - 1)
            return quickSelectFirst(data, partition + 1, r, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else
            return quickSelectFirst(data, l, partition - 1, k);
    }
    public static int quickSelectRand(int [] data, int l, int r, int k){
        int partition = partition(data, l, erzeugeZufallRandom(data[l],data[r]), r);

        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return data[partition];

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k - 1)
            return quickSelectRand(data, partition + 1, r, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else
            return quickSelectRand(data, l, partition - 1, k);
    }
    public static int erzeugeZufallRandom(int untereGrenze, int obereGrenze){
        Random zufall = new Random(); // neues Objekte der Random Klasse
        int zufallZahl = zufall.nextInt(obereGrenze); // next Int Methode mit Argument

        while (zufallZahl < untereGrenze){ //Beding. zufallsZahl muss kleiner als Argument sein
            zufallZahl = zufall.nextInt(obereGrenze); //finde nächste Zahl
        }
        return zufallZahl;
    }
    public static int assertion(int[] data, int k){
        Arrays.sort(data);
        return data[k-1];
    }
}
