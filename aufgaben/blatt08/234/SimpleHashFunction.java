public interface SimpleHashFunction<K> {
    int getHash(K key);
}
// Ist SHF ein inneres Interface in der SimpleHT-Klasse, kann die Testklasse dieses nicht finden