package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private final int top;
    private final int bottom;
    private final int size;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n) {
    	if (n <= 0) {
            throw new IllegalArgumentException("n ≤ 0 ");
        }
    	top = 0;
        size = n;
        bottom = size * size + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);
        grid = new boolean[size][size];
    }

    public void open(int row, int col) {
    	if (!isValidRowAndCol(row, col)) {
            throw new IllegalArgumentException("index out of range");
        }
    	
        grid[row - 1][col - 1] = true;
        if (row == 1) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), top);
        }
        if (row == size) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), getCorrespondNumber(row, col - 1));
        }
        
        if (col < size && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), getCorrespondNumber(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), getCorrespondNumber(row - 1, col));
        }
        
        if (row < size && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(getCorrespondNumber(row, col), getCorrespondNumber(row + 1, col));
        }
    }

    public boolean isOpen(int row, int col) {
    	if (!isValidRowAndCol(row, col)) {
            throw new IllegalArgumentException("index out of range");
        }
        return grid[row - 1][col - 1];
    }

	public boolean isFull(int row, int col) {
        if (isValidRowAndCol(row, col)) {
            return (weightedQuickUnionUF.find(top) ==
            		weightedQuickUnionUF.find(getCorrespondNumber(row, col)));
        } else {
            throw new IllegalArgumentException("index out of range");
        }
    }

	public boolean percolates() {
    	return (weightedQuickUnionUF.find(top) == weightedQuickUnionUF.find(bottom));
    }
    
    public int numberOfOpenSites() {
    	int openSites = 0;
    	for (int row = 0; row < size; row++) {
    		for (int col = 0; col < size; col++) {
    			if (grid[row][col]) openSites++;
    		}
    	}
    	return openSites;
    }
    
    private int getCorrespondNumber(int row, int col) {
        return size * (row - 1) + col;
    }
    
    private boolean isValidRowAndCol(int row, int col) {
    	return (0 < row && row <= size && 0 < col && col <= size);
    }
}
