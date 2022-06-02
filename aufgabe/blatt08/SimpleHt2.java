import java.sql.Array;
import java.util.*;
import java.lang.Math;

public class SimpleHt {
    private ArrayList<ArrayList<Pair>> table;
    private Integer c;

    public SimpleHT(int m) {
        c = m;
        table = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i < c; i++) {
            table.add(i, new ArrayList<Pair>());
        }
    }

    private class Pair {
        private Integer key,value;

        public Pair(Integer x, Integer y) {
            key = x;
            value = y;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {return value;}



    }

    private Integer addressOfList(Integer key) {
        return Math.floorMod(key, c);
    }

    public void insert(Integer key, Integer value) {
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

    public Integer get(Integer key) {
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

    public boolean remove(Integer key) {
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

