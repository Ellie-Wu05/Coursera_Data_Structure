/*Percolation. Given a composite systems comprised of randomly distributed insulating and metallic materials:
 what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to
 drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process
 known as percolation to model such situations.*/

public class Percolation {

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        /
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)

    // is the site (row, col) full?
    public boolean isFull(int row, int col)

    // returns the number of open sites
    public int numberOfOpenSites()

    // does the system percolate?
    public boolean percolates()

    // test client (optional)
    public static void main(String[] args)
}
