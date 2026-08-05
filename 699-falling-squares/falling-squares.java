import java.util.*;

class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<int[]> intervals = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        int maxHeight = 0;

        for (int[] p : positions) {
            int left = p[0];
            int right = left + p[1];
            int height = p[1];

            int base = 0;

            // Find the highest overlapping interval
            for (int[] in : intervals) {
                int l = in[0];
                int r = in[1];
                int h = in[2];

                if (left < r && right > l) {
                    base = Math.max(base, h);
                }
            }

            height += base;

            intervals.add(new int[]{left, right, height});

            maxHeight = Math.max(maxHeight, height);
            ans.add(maxHeight);
        }

        return ans;
    }
}