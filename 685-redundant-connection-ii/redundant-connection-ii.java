class Solution {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] cand1 = null;
        int[] cand2 = null;

        // Step 1: Check if a node has two parents
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                cand1 = new int[]{parent[v], v};
                cand2 = new int[]{u, v};
                edge[1] = 0; // Mark second edge as invalid temporarily
            }
        }

        // Union-Find
        int[] uf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            uf[i] = i;
        }

        for (int[] edge : edges) {
            if (edge[1] == 0) continue;

            int u = edge[0];
            int v = edge[1];

            int pu = find(uf, u);

            if (pu == v) {
                if (cand1 == null)
                    return edge;
                return cand1;
            }

            uf[v] = pu;
        }

        return cand2;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}