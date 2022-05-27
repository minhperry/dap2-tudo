public class BitVectorRank {
    private BitVector bv;
    private int[] r;
    int c;

    public BitVectorRank (BitVector bitvec, int c) {
        bv = bitvec;
        r = new int[bitvec.size() / c + 2];
        this.c = c;
        // Anzahl der 1-Bits bis bitvec.size()/c
        for (int i = 0; i < bitvec.size(); ++i) {
            if (bitvec.get(i)) ++r[i / c];
        }
        // kummulative Summe von Einsen
        for (int i = 1; i < r.length; ++i) {
            r[i] += r[i - 1];
        }
    }


    public int size() {
        return bv.size();
    }

    public int rank(int index) {
        int sum = 0;
        // rank(i') berechnen
        if( index >= c ) sum = r[(index/c)-1]; 
        // rank(i' -> i-1) berechnen
        for(int i =(index/c)*c; i < index; ++i) {
            if(bv.get(i)) {
                ++sum;
            }
        }
        return sum;

    }

    public int count(int start, int end) {
        int a = rank(end) - rank(start);
        return a;
    }
}
