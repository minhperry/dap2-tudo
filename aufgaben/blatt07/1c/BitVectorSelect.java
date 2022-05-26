class BitVectorSelect {
    private BitVectorRank bvr;

    public BitVectorSelect(BitVectorRank bitvecrank) {
        bvr = bitvecrank;
    }

    public int size() {
        return bvr.size();
    }

    public int select(int k) {
        if (k < 1 || bvr.rank(bvr.size() - 1) < k) {
            return -1;
        }
        k = k - 1; // 0-Indexing
        int low = 0, high = bvr.size();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bvr.rank(mid) == k) {
                return mid;
            }
            if (bvr.rank(mid) < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int select(int k, int start, int end) {
        if (k < 1 || bvr.rank(end) - bvr.rank(start) < k) {
            return -1;
        }
        k = k - 1; // 0-Indexing
        int low = start, high = end;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bvr.rank(mid) == k) {
                return mid;
            }
            if (bvr.rank(mid) < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}