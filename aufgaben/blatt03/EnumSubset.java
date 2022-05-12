import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EnumSubset {

    public static int removeDuplicates(int[] data) {
        int l = data.length;
        // Um Duplikate einfach zu entfernen soll man zuerst das Array sortieren, 
        // damit die Duplikate nebeneinader sind.
        Arrays.sort(data);
        // Falls das Array leer ist, wird -1 zurückgegeben
        if (l <= 0) {
            System.out.println("Empty array does not have duplicates!");
            return -1;
        } else {
            // Index j verfolgt die Anzahl von Nichtduplikaten...
            int j = 0;
            // ... und Index i iterieren einfach durch das Array.
            for (int i = 0; i < l - 1; i++) {
                // Wenn ein und sein folgendes Element unterschiedlich sind,
                // wird es mit der j-Index ins Array gespeichert. Sonst wird
                // das Duplikat überspringen.
                if (data[i] != data[i + 1]) {
                    data[j++] = data[i];
                }
            }
            // Da die for-Schleife nur bis Index l-2 iteriert, vermissen wir noch die 
            // letzte Element des Arrays. Dann müssen wir noch dieses kopieren.
            data[j] = data[l - 1];
            return ++j;
        }
    }

    // Hilfsmethode, welche die obige Array ohne Duplikate zurückgibt.
    public static int[] noDuplicate(int[] data) {
        // leeres Array fangen
        if (removeDuplicates(data) == -1) {
            return new int[] {};
        } else {
            // sonst einfach die Werte in ein neues Array kopieren
            int[] nodupe = new int[removeDuplicates(data)];
            for (int i = 0; i < nodupe.length; i++) {
                nodupe[i] = data[i];
            }
            return nodupe;
        }
    }

    public static int amountCombis = 0;

    /*
        data    Eingabearray
        combi   Temporäre Array zum Speichern von diezeitigen Kombination
        s, e    Anfangs- und Endindex im Eingabearray
        idx     Derseitige Index im temporären Array
    */
    public static void combination(int[] data, int[] combi, int s, int e, int idx) {
        // Eine Kombination ist schon fertig und kann ausgedrückt werden
        if (idx == combi.length) {
            System.out.println(Arrays.toString(combi));
            amountCombis++;
            return;
        }
        // combi.length - idx => leeres Teil im temp. Array das noch füllen muss
        // e - i + 1 => nicht gescanntes Teil im data-Array
        for (int i = s; i <= e && e - i + 1 >= combi.length - idx; i++) {
            // combi-Array mit allen möglichen Elementen füllen
            combi[idx] = data[i];
            // Bei jeder Rekursion werden die nichtgefüllten Elementen in
            // combi-Array ausgefüllt, bis das Array voll ist.
            combination(data, combi, i + 1, e, idx + 1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> eingabe = new ArrayList<>();
        while (sc.hasNextLine()) {
            try {
                int val = Integer.parseInt(sc.nextLine());
                eingabe.add(val);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number!");
                return;
            }
        }

        int[] zahlen = new int[eingabe.size()];
        for (int i = 0; i < zahlen.length; i++) {
            zahlen[i] = eingabe.get(i).intValue();
        }
        // Originalordnung speichern, damit man später ausdrücken kann
        String originalOrder = Arrays.toString(zahlen);

        int k = 0;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input k is not a number!");
            return;
        }

        int[] zahlenNoDupe = noDuplicate(zahlen);

        System.out.println("Before removing duplicates: " + originalOrder);
        System.out.println("After removing duplicates: " + Arrays.toString(zahlenNoDupe));

        if (k > zahlenNoDupe.length) {
            System.out.println("k is too large.");
            return;
        } 
        if (k < 0) {
            System.out.println("k is too small.");
            return;
        }

        // temp. Array für combination()
        int[] temp = new int[k];
        combination(zahlenNoDupe, temp, 0, zahlenNoDupe.length - 1, 0);

        System.out.println("There are " + amountCombis + " subset(s).");
        
    }

}
