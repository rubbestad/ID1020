package Searching;

public class BST <Key extends Comparable<Key>, Value> {

    private Node root;
    // root of BST
    private class  Node
    {
        private Key key; // key
        private Value val;
        // associated value
        private Node left, right;
        // links to subtrees
        private int N;
        // # nodes in subtree rooted here
        public Node(Key key, Value val, int N)
        {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public  Value get(Key key) {
        if (key == null)
            return null;

        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left  = put(x.left,  key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //contains function
    public boolean contains(String word) {
        if(root == null)
            return false;
        String[] list = new String[root.N];
        list = keys();
        for (String w : list)
            if (w != null)
                if(w.compareTo(word) == 0)
                    return true;
        return false;
    }

    public String[] keys() {
        Queue q = new Queue();
        int arraySize = root.N;
        keyChain(root, q);
        String[] ret = new String[arraySize];

        for (int i=0; i<arraySize-1; i++)
            ret[i] = q.dequeue();

        return ret;
    }

    private void keyChain(Node x, Queue q) {
        if (x == null)
            return;

        if(x.key.compareTo((Key) "") == 0)
            return;

        q.enqueue((String) x.key);
        keyChain(x.left, q);
        keyChain(x.right, q);
    }

    public void print() {
        String[] k = keys();
        for (int i=0; i<k.length; i++) {
            if(k[i] != null)
                System.out.print("["+k[i]+","+get((Key) k[i])+"]");
        }
        System.out.print("\n");
    }
}