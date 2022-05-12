import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class DasAuswahlproblem {
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
        System.out.println(exactSelect(array,4));

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
    public static int exactSelect(int[] data, int k){
        int max = getMax(data);
        int min = getMin(data);
        int[] count = count(data, min, max);
        int a = 0;
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0){
                a++;
            }
            if(a == k){
                return i + min;
            }
        }
        return max;
    }
}
