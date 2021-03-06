package assignment.puzzle;
import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
	
    private final Stack<Board> solutionStack = new Stack<Board>();
    private boolean mSolvable = true;
    
    
    private class Node {
        Board board;
        int steps;
        Node parent;
        
        Node(Board b, int s, Node n) {
            board = b;
            steps = s;
            parent = n;
        }
    }
    
    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();
        
        Node node = new Node(initial, 0, null);
        Node nodeTwin = new Node(initial.twin(), 0, null);
        MinPQ<Node> heap = new MinPQ<Node>(new Priority());
        MinPQ<Node> heapTwin = new MinPQ<Node>(new Priority());
        heap.insert(node);
        heapTwin.insert(nodeTwin);
        while (!heap.isEmpty()) {
            
            nodeTwin = heapTwin.delMin();
            if (nodeTwin.board.isGoal()) { 
                mSolvable = false;
                return;
            }
            
            node = heap.delMin();
            if (node.board.isGoal()) {
                makeSolutions(node);
                return;
            } else {
                Node child;
                Node childTwin;
                boolean guardChild = true;  // used for optimization
                boolean guardChildTwin = true;
                for (Board b : node.board.neighbors()) {
                    if (guardChild) {
                        if (node.parent != null && b.equals(node.parent.board)) {
                            guardChild = false;
                            continue;
                        } else {
                            child = new Node(b, node.steps + 1, node);
                            heap.insert(child);
                        }
                    } else {
                        child = new Node(b, node.steps + 1, node);
                        heap.insert(child);
                    }
                }
                for (Board b : nodeTwin.board.neighbors()) {
                    if (guardChildTwin) {
                        if (nodeTwin.parent != null && b.equals(nodeTwin.parent.board)) {
                            guardChildTwin = false;
                            continue;
                        } else {
                            childTwin = new Node(b, nodeTwin.steps + 1, nodeTwin);
                            heapTwin.insert(childTwin);
                        }
                    } else {
                        childTwin = new Node(b, nodeTwin.steps + 1, nodeTwin);
                        heapTwin.insert(childTwin);
                    }
                }
            }
        }
    }
    
    private class Priority implements Comparator<Node> {
        public int compare(Node a, Node b) {
            int m1 = a.board.manhattan();
            int m2 = b.board.manhattan();
            int d1 = m1 + a.steps;
            int d2 = m2 + b.steps;
            if (d1 < d2) {
                return -1;
            } else if (d1 == d2) {
                if (m1 < m2) return -1;
                if (m1 > m2) return 1;
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    private void makeSolutions(Node node) {
        while (node != null) {
            solutionStack.push(node.board);
            node = node.parent;
        }
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {
        return mSolvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return solutionStack.size() - 1;
        else 
            return -1;
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable())
            return solutionStack;
        else 
            return null;
    }

    // test client (see below) 
    public static void main(String[] args) {
    	// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
        	for (int j = 0; j < n; j++)
        		tiles[i][j] = in.readInt();
        
        Board initial = new Board(tiles);

        // solve the assignment.puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
