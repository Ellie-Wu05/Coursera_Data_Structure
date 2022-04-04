public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i; // set self id as itself first
        }
    }

    private int root (int i){
        // 找root
        while(id[i]!=i){ //chase parent root until reahc the end
            i= id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p)==root(q);
    }

    public void union(int p, int q){

        int i = root(p);
        int j = root(q);
        // change root of p to root of q
        id[i] =j;
    }
}
