import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int ntrials;
    private double [] fractions;
    private final double Confidence_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
       if (n<=0 || trials <=0){ throw new IllegalArgumentException();}
        ntrials = trials;
        fractions= new double[trials];
        for (int i = 1; i <= ntrials; i++) {

            Percolation simulation = new Percolation(n);
            while (simulation.percolates() ==false){
                // generate random coordinates
                int r = StdRandom.uniform(n) +1;
                int c = StdRandom.uniform(n) +1;
                if(! simulation.isOpen(r,c)){
                    simulation.open(r,c);
                }

        }
            int opensites = simulation.numberOfOpenSites();
            double frac = (double)opensites/(n*n);
            fractions[i-1] = frac;
       }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - Confidence_95*stddev()/Math.sqrt(ntrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + Confidence_95*stddev()/Math.sqrt(ntrials);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = 10; //Integer.parseInt(args[0]);
        int T = 3; //Integer.parseInt(args[1]);
        PercolationStats sample = new PercolationStats(n,T);
        StdOut.println("Mean:" + sample.mean());
        StdOut.println("Std:" + sample.stddev());
        StdOut.println("Confidence interval:" + sample.confidenceLo() + '-' + sample.confidenceHi());


    }
}
