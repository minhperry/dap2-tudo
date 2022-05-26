public class BitVector {
    private int n;
    private int[] word;

    public BitVector(int n) {
        if (n < 0) {
            throw new NegativeArraySizeException();
        } else {
            this.n = n;
            this.word = new int[n / 32 + 1];
        }
    }

    public int size() {
        return n;
    }

    public boolean get(int index) {
        int value = word[index / 32];
        value = value >> (index % 32);
        int mask = 1;
        return (value & mask) > 0;
    }

    public void set(int index, boolean value) {
        if (!get(index) && value) {
            int mask = 1 << (index % 32);
            word[index / 32] = word[index / 32] | mask;
        } else if (get(index) && !value) {
            int mask = 1 << (index % 32);
            mask = ~mask;
            word[index / 32] = word[index / 32] & mask;
        }
    }
}
