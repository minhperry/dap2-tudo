import com.sun.jdi.InterfaceType;

import java.util.*;
import java.lang.Math;

public class SimpleHT<K, V> {
    private ArrayList<ArrayList<Pair<K, V>>> table;
    private Integer c;
    private SimpleHashFunction<K> obj;

    public SimpleHT(int m) {
        c = m;
        table = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            table.add(i, new ArrayList<>());
        }
    }


    public SimpleHT(int m, SimpleHashFunction<K> obj){
        c = m;
        obj = obj;
        table = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            table.add(i, new ArrayList<>());
        }
    }

    private class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K x, V y) {
            key = x;
            value = y;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {return value;}



    }

    public Integer addressOfList(K key){
        int code = key.hashCode();
        if(obj != null) {
            code = obj.getHash(key);
        }
        return Math.floorMod(code, c);
    }

    public void insert(K key, V value) {
        if (table.get(addressOfList(key)).size() == 0) {
            table.get(addressOfList(key)).add(new Pair(key, value));
        } else {
            for (int i = 0;i < table.get(addressOfList(key)).size(); i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)){
                    table.get(addressOfList(key)).get(i).value = value;
                    return;
                }
            }
            table.get(addressOfList(key)).add(new Pair(key, value));
        }
    }

    public V get(K key) {
        if (table.get(addressOfList(key)).size() == 0) {
            return null;
        } else {
            for (int i = 0;i < table.get(addressOfList(key)).size() ; i++) {
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
            for (int i = 0;i < table.get(addressOfList(key)).size() ; i++) {
                if (table.get(addressOfList(key)).get(i).key.equals(key)) {
                    table.get(addressOfList(key)).remove(i);
                    return true;
                }
            }
            return false;
        }
    }
}
