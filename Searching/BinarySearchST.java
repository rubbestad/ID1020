package Searching;

public class BinarySearchST <Key extends Comparable<Key>,Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        // See Algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public int size() {
        return N;
    }
    private void resize(int max)
    {	// Move stack to a new array of size max.
        Value[] temp = (Value[]) new Object[max];
        Key[] tempKey = (Key[]) new Comparable[max];
        for (int i = 0; i < N; i++)
            temp[i] = vals[i];
        vals = temp;
        for (int i = 0; i < N; i++)
            tempKey[i] = keys[i];
        keys = tempKey;
    }
    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }
    //returns which spot this key has in the rank list.
    public int  rank(Key key) {
        int lo = 0,
                hi = N-1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);

        //check if we need to increase the array's length
        if (N == keys.length)
            resize(2*keys.length);

        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        boolean removed = false; // to toggle for use of overwrite-chain
        for (int i=0; i < N; i++) //loops through the array to find element
            if (removed)
                keys[i] = keys[i+1];
            else if (keys[i].compareTo(key) == 0) { // if found element, start an overwrite-chain
                removed = true;
                N--;
                keys[i] = keys[i+1];
            }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public String[] keys() {
        String[] ret = new String[N];
        for (int i=0; i < N; i++)
            ret[i] = keys[i].toString();
        return ret;
    }

    public boolean contains(Key word) {
        for (int i=0; i<N; i++)
            if (keys[i] != null)
                if(keys[i].compareTo(word) == 0)
                    return true;
        return false;
    }
}
