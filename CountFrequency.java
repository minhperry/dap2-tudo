import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CountFrequency {
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
        int max = getMax(array);
        int min = getMin(array);
        int[] count = count(array, min, max);
        System.out.println("The minimum value:" + min);
        System.out.println("The maximum value:" + max);
        System.out.println(Arrays.toString(count));
    }
    public static int getMin(int[] data){
        int min = data[0];
        for( int i : data ){
            if( i < min ){
                min = i;
            }
        }
        return min;
    }
    public static int getMax(int[] data){
        int max = data[0];
        for( int i : data ){
            if( i > max ){
                max = i;
            }
        }
        return max;
    }
    public static int[] count(int[] data, int min, int max){
        int[] C = new int[max - min + 1];
        for(int i : data){
            C[i-min]++;
        }
        return C;
    }
}
