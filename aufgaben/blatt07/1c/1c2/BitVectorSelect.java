class BitVectorSelect {
    private BitVectorRank bvr;

    public BitVectorSelect(BitVectorRank bitvecrank) {
        bvr = bitvecrank;
    }

    public int size() {
        return bvr.size();
    }

    // Gleiches Konzept
    public int select(int k, int start, int end) {
        if (k < 1 || bvr.rank(end) - bvr.rank(start) < k) {
            return -1;
        }
        int left = start, right = end;
        int mid, val, res = -1; 
        int offset = bvr.rank(start);
        while (left <= right) {
            mid = (left + right) / 2;
            val = bvr.rank(mid) - offset;
            if (val >= k) {
                if (val == k) res = mid - 1;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }   
        return res;
    }
    
    public int select(int k) {
        if (k < 1 || bvr.rank(bvr.size()) < k) {
            return -1;
        }
        // Rechts- und Linksgrenze
        int left = 0, right = bvr.size();
        // mid -> mittlere Index für binäre Suche
        // val -> Rank am Index mid
        // res -> Rückgabewert
        int mid, val, res = -1;
        // Binäre Suche: nimmt mid als mittlere Wert sowie die Rank dessen Indexes
        // Falls diese Rank größer als gesuchte Rank k, wird mid die neue rechte Grenze
        // Sonst wird mid die neue linke Grenze
        while (left <= right) {
            mid = (left + right) / 2;
            val = bvr.rank(mid);
            if (val >= k) {
                if (val == k) res = mid - 1;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }   
        return res;
    }

}
