import java.util.BitSet;

public class BloomFilter<T> {

    private final int m; // size of array/BitSize
    private final int k; // number of hash functions.
    private final BitSet arr;

    public BloomFilter(int m, int k){
        this.m = m;
        this.k = k;
        this.arr = new BitSet(m);
    }

    // Adds an element by setting k bits to true
    public void add(T element){
        for(int i=0;i<k;i++){
            int hash = getHash(element, i);
            arr.set(Math.abs(hash%m));
        }
    }

    // Returns false if definitely not present
    // Returns true if potentially present
    public boolean mightContain(T element){
        for(int i=0;i<k;i++){
            int hash = getHash(element, i);
            if(!arr.get(Math.abs(hash%m))){
                return false;
            }
        }
        return true;
    }

    private int getHash(T element, int seed){
        int hash = element.hashCode();
        return hash ^ (seed * 0x5bd1e995); // 0x5bd1e995 is a magic constant (from MurmurHash)
    }

    public static void main(String[] args) {
        BloomFilter<String> bf = new BloomFilter<>(1000, 3);

        bf.add("ramsy");
        bf.add("anshu");

        System.out.println(bf.mightContain("ramsy"));       // true
        System.out.println(bf.mightContain("anshu"));       // true
        System.out.println(bf.mightContain("sudhanshu"));   // false
    }
}
