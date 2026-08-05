import java.util.*;

class Solution {
    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int x : cards) {
            nums.add((double) x);
        }
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < EPS;
        }

        int n = nums.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                List<Double> next = new ArrayList<>();

                // Keep remaining numbers
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                double a = nums.get(i);
                double b = nums.get(j);

                List<Double> candidates = new ArrayList<>();
                candidates.add(a + b);
                candidates.add(a - b);
                candidates.add(b - a);
                candidates.add(a * b);

                if (Math.abs(b) > EPS)
                    candidates.add(a / b);

                if (Math.abs(a) > EPS)
                    candidates.add(b / a);

                for (double val : candidates) {
                    next.add(val);
                    if (dfs(next))
                        return true;
                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }
}