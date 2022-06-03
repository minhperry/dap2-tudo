import java.util.*;
import java.lang.Math;

public class SimpleHT {

    private class Pair {
        private Integer key;
        private Integer value;

        public Pair(Integer x, Integer y) {
            key = x;
            value = y;
        }
    }

    private ArrayList<ArrayList<Pair>> table;
    private Integer m;

    public SimpleHT(int M) {
        m = M;
        table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            table.add(i, new ArrayList<>());
        }
    }

    // addressOfList mit floorMod statt %
    public Integer addressOfList(Integer key) {
        return Math.floorMod(key, m);
    }

    // Falls das ArrayList für key in der HT leer ist, fügen wir dieses als erstes Element ein.
    // Sonst überschreiben falls existiert und dann einfügen.
    public void insert(Integer key, Integer value) {
        if (table.get(addressOfList(key)).size() == 0) {
            table.get(addressOfList(key)).add(new Pair(key, value));

        } else {
            for (int i = 0; i < table.get(addressOfList(key)).size(); i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)) {
                    table.get(addressOfList(key)).get(i).value = value;
                    return;
                }
            }
            table.get(addressOfList(key)).add(new Pair(key, value));
        }
    }

    // leeres ArrayList? -> null
    // nicht leeres ArrayList? -> durchlesen, zugehöriger Wert  
    // Schlüssel nicht gefunden? -> null
    public Integer get(Integer key) {
        if (table.get(addressOfList(key)).size() == 0) {
            return null;
        } else {
            for (int i = 0; i < table.get(addressOfList(key)).size(); i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)) {
                    return table.get(addressOfList(key)).get(i).value;
                }
            }
            return null;
        }
    }

    // leeres ArrayList? -> false
    // nicht leeres ArrayList? -> durchlesen, löschen + true
    // Schlüssel nicht gefunden? -> false
    public boolean remove(Integer key) {
        if (table.get(addressOfList(key)).size() == 0) {
            return false;
        } else {
            for (int i = 0; i < table.get(addressOfList(key)).size(); i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)) {
                    table.get(addressOfList(key)).remove(i);
                    return true;
                }
            }
            return false;
        }
    }
}
