package others.incourse;

/**
 A class for Weighted Quick Union with Path Compression (WQUPC)
 <p>
 Union-find applications:
     • Network connectivity.
     • Percolation.
     • Image processing.
     • Least common ancestor.
     • Equivalence of finite state automata.
     • Hinley-Milner polymorphic type inference.
     • Kruskal's minimum spanning tree algorithm.
     • Games (Go, Hex)
     • Compiling equivalence statements in Fortran.
 </p>
 */
public class QuickUnionFind {
    private int[] parent; // each element keeps parent number
    private int[] size; // size of tree for each element
    private int count; // number of components. component means subtrees

    /**
     Constructor : To create array filled by 1-n
     @param n is the size of array
     */
    public QuickUnionFind(int n) {
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];

        for (int i=0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    /**
     To find i node's parent (another name of this method is "root")
     @param i is the node we want to find its parent
     @return parent
     */
    public int find(int i) {
        while (i != this.parent[i]) {
            //halving tree and path compression
            this.parent[i] = this.parent[this.parent[i]];

            i = this.parent[i];
        }
        return i;
    }

    /**
     Check if two nodes are connected or not
     @param p node
     @param q node
     */
    public boolean connected(int p, int q) {
        return (find(p) == find(q));
    }

    /**
     Connect two nodes with a consideration: smaller has to merge the bigger one
     @param p node
     @param q node
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (rootP > rootQ) {
            //rule: smaller has to merge bigger
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
        } else {
            //rule: smaller has to merge bigger
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];
        }

        this.count--;
    }

}
