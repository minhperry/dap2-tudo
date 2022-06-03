import java.util.*;
import java.lang.Math;

public class SimpleHT<K, V> {

    private class Pair<k, v> {
        private K key;
        private V value;

        public Pair(K x, V y) {
            key = x;
            value = y;
        }
    }

    private ArrayList<ArrayList<Pair<K, V>>> table;
    private Integer c;
    private SimpleHashFunction<K> shf;

    
    public SimpleHT(int m) {
        c = m;
        table = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            table.add(i, new ArrayList<>());
        }
    }
    

    public SimpleHT(int m, SimpleHashFunction<K> shfobj) {
        c = m;
        shf = shfobj;
        table = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            table.add(i, new ArrayList<>());
        }
    }

    // gleiches Konzept, key.hashCode() statt key
    public Integer addressOfList(K key) {
        int code = key.hashCode();
        if (shf != null) {
            code = shf.getHash(key);
        }
        return Math.floorMod(code, c);
    }

    public void insert(K key, V value) {
        if (table.get(addressOfList(key)).size() == 0) {
            table.get(addressOfList(key)).add(new Pair<K, V>(key, value));
        } else {
            for (int i = 0; i < table.get(addressOfList(key)).size(); i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)) {
                    table.get(addressOfList(key)).get(i).value = value;
                    return;
                }
            }
            table.get(addressOfList(key)).add(new Pair<K, V>(key, value));
        }
    }

    public V get(K key) {
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

    public boolean remove(K key) {
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
