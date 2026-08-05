import java.util.*;

class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();

        // Collect all trees
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        // Sort by height
        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int sr = 0, sc = 0;

        for (int[] tree : trees) {
            int dist = bfs(forest, sr, sc, tree[1], tree[2]);
            if (dist == -1) return -1;

            ans += dist;
            sr = tree[1];
            sc = tree[2];
        }

        return ans;
    }

    private int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        if (sr == tr && sc == tc) return 0;

        int m = forest.size();
        int n = forest.get(0).size();

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;
                if (visited[nr][nc] || forest.get(nr).get(nc) == 0)
                    continue;

                if (nr == tr && nc == tc)
                    return cur[2] + 1;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, cur[2] + 1});
            }
        }

        return -1;
    }
}