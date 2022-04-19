/*Percolation. Given a composite systems comprised of randomly distributed insulating and metallic materials:
 what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to
 drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process
 known as percolation to model such situations.*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean [][] table;
    private  int size;
    private int top =0 ;
    private int bottom;
    private WeightedQuickUnionUF qf;
    private int opensites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        table = new boolean[n][n];
        bottom = size * size +1;
        qf = new WeightedQuickUnionUF(n*n +2); // n*n grid + one top dummy + one bottom dummy
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = false;                // in the beginning, it is closed
            }
        }
        opensites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        checkException(row,col);

        if (isOpen(row-1,col-1) ==false){
            table[row-1][col-1] =true; // open site
            opensites +=1;             // number of open sites + 1 corrrespondingly

            if (row==1){
                qf.union(oneD_index(row,col),top);   // connect first row to the top node
            }
            if (row==size){
                qf.union(oneD_index(row,col),bottom); // connect last row to the bottom node
            }

            if(row>1 && isOpen(row-1,col)){
                qf.union(oneD_index(row,col),oneD_index(row-1,col)); // connect current to the above one
            }

            if (row>1 && isOpen(row+1,col)){
                qf.union(oneD_index(row,col),oneD_index(row+1,col)); // connect current to the below one
            }
            if (row>1 && isOpen(row,col+1)){
                qf.union(oneD_index(row,col),oneD_index(row,col+1)); // connect current to the right one
            }
            if (row>1 && isOpen(row,col-1)){
                qf.union(oneD_index(row,col),oneD_index(row,col-1)); // connect current to the left one
            }

        }

    }

    // craete corresponding 1D index rather than 2D index of row and column
    public int oneD_index(int row, int col){
        return size * (row-1) + col;    // if n = 6, index 5 in row 1, col 5  = 6*(1-1) + 5
    }

    // check exceptions
    public void checkException(int row, int col){
        if (row<=0 || row >size || col <= 0 || col>size){
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        checkException(row,col);
        return table[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkException(row,col);
        return qf.find(top) == qf.find(oneD_index(row,col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return opensites;
    }

    // does the system percolate?
    public boolean percolates(){
        return qf.find(top) == qf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation test = new Percolation(100);
    }
}
