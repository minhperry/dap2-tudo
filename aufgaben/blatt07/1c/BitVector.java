import java.util.*;
import java.math.*;

class BitVector {
    private int size;
    private int[] bits;

    public BitVector(int n) {
        size = n;
        bits = new int[(int) Math.ceil(n / 32.0)];
    }

    public int size() {
        return size;
    }

    public boolean get(int index) {
        int which = bits[index / 32];
        int shifted = which >> index;
        return (shifted & 1) > 0;
    }

    public void set(int index, boolean value) {
        int which = bits[index / 32];
        int mask = 1 << index;
        if (value) {
            which = which | mask;
        } else {
            mask = ~mask;
            which = which & mask;
        }
        bits[index / 32] = which;
    }
}