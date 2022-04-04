import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] parent;   //parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    public WeightedQuickUnionUF(int n){
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] =i;
            size[i] =1;
        }
    }

    public int count(){return count;}

    public int find(int p){
        validate(p);
        while(parent[p] != p){  //找root
            p = parent[p];
        }
        return p;
    }

    @Deprecated
    public boolean connected(int p, int q){
        return find(p)==find(q);
    }

    private void validate(int p){
        int n = parent.length;
        if (p<0 || p>n){
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    private void union(int p ,int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP==rootQ) return;

        if (size[rootP] < size[rootQ]){
            parent[p] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[q] = rootP;
            size[rootP] += size[rootQ];
        }
        count --;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        edu.princeton.cs.algs4.WeightedQuickUnionUF uf = new edu.princeton.cs.algs4.WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
