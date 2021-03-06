package puzzle;

import java.util.LinkedList;
import java.util.Queue;


public class Board {
	
	private final int[][] board;
	private final int size;
	
    public Board(int[][] tiles) {
    	this.size = tiles.length;
    	this.board = new int[size][size];
    	for (int i = 0; i < size; i++) {
    		for (int j = 0; j < size; j++) {
    			this.board[i][j] = tiles[i][j];
    		}
    	}
    }
                                           
    public String toString() {
    	StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(board[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int dimension() {
    	return this.size;
    }

    // number of tiles out of place
    public int hamming() {
    	int counter = 0;
    	
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
    			if ((i-1)*size +j != this.board[i][j]) {
    				counter++;
    			}
    		}
    	}
    	
    	return counter;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int dist = 0;
    	
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
    			if (board[i][j] != 0) {
 
	    			int row = this.board[i][j] / dimension();
	    			int col = this.board[i][j] % dimension() - 1;
	    			dist += Math.abs(row - i) + Math.abs(col - j);
    			}
    		}
    	}
    	
    	return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	return (manhattan() == 0);
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	boolean isEqual = false;
    	if (Object.class.isInstance(Board.class) && 
    			toString().equals(y.toString())) {
    		isEqual = true;
        }
        return isEqual;
    }

    // all neighbors boards
    public Iterable<Board> neighbors() {   	
        int[][] neighbor = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                neighbor[i][j] = board[i][j];
        
        int row = 0;
        int col = 0;
        boolean found = false;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        
        Queue<Board> neighborsList = new LinkedList<Board>();
        
        if (row > 0) {
            swap(neighbor, row, col, row - 1, col);
            neighborsList.add(new Board(neighbor));
            swap(neighbor, row, col, row - 1, col);
        }
        if (row < size - 1) {
            swap(neighbor, row, col, row + 1, col);
            neighborsList.add(new Board(neighbor));
            swap(neighbor, row, col, row + 1, col);
        }
        if (col > 0) {
            swap(neighbor, row, col, row, col - 1);
            neighborsList.add(new Board(neighbor));
            swap(neighbor, row, col, row, col - 1);
        }
        if (col < size - 1) {
            swap(neighbor, row, col, row, col + 1);
            neighborsList.add(new Board(neighbor));
            swap(neighbor, row, col, row, col + 1);
        }
        return neighborsList;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	return null;
    }

    
    private void swap(int[][] copy, int row, int col, int newRow, int newCol) {
        int tmp = copy[row][col];
        copy[row][col] = copy[newRow][newCol];
        copy[newRow][newCol] = tmp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    	
    }
    

}