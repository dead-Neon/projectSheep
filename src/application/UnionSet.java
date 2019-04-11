package application;

public class UnionSet {

	int[] rank, parent;
	int n;

	public UnionSet(int n) {
		rank = new int[n];
		parent = new int[n];
		this.n = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i; // Each element is parent initially thus rank is 0
		}
	}

	public int[] getRank() {
		return rank;
	}

	public void setRank(int[] rank) {
		this.rank = rank;
	}

	public int[] getParent() {
		return parent;
	}

	public void setParent(int[] parent) {
		this.parent = parent;
	}

	int find(int i) {
		if (parent[i] != i) {
			return find(parent[i]); // Recur through parents of each element to find representative of set
		}
		return i;
	}

	void union(int node1, int node2) {
		int x = find(node1);
		int y = find(node2);

		if (x == y) // match, same representative
		{
			return;
		}

		if (rank[x] < rank[y]) // Set parent as lowest rank to set least depth of trees
		{
			parent[x] = y;
		} else if (rank[y] < rank[x]) {
			parent[y] = x;
		}

		else // same rank, add to either and increase rank
		{
			parent[y] = x;
			rank[x] = rank[x] + 1;
		}
	}
}
