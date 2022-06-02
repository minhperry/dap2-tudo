import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SimpleHT {
    private class Pair {
        private final Integer key;
        private Integer value;

        public Pair(Integer k, Integer v) {
            key = k;
            value = v;
        }
    }

    // https://cs.brynmawr.edu/cs151/L08/lec08.pdf

    private ArrayList<Pair> pairs;
    private int m;

    public SimpleHT(int size) {
        m = size;
    }

}
