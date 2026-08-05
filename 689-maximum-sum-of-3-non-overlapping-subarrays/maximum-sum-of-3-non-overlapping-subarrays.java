class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Sum of every subarray of length k
        int[] windowSum = new int[n - k + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                windowSum[i - k + 1] = sum;
            }
        }

        int m = windowSum.length;

        // left[i] = index of max window from 0 to i
        int[] left = new int[m];
        int best = 0;
        for (int i = 0; i < m; i++) {
            if (windowSum[i] > windowSum[best]) {
                best = i;
            }
            left[i] = best;
        }

        // right[i] = index of max window from i to end
        int[] right = new int[m];
        best = m - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (windowSum[i] >= windowSum[best]) {
                best = i;
            }
            right[i] = best;
        }

        int[] ans = new int[3];
        int maxTotal = 0;

        // Middle subarray
        for (int mid = k; mid < m - k; mid++) {
            int l = left[mid - k];
            int r = right[mid + k];

            int total = windowSum[l] + windowSum[mid] + windowSum[r];

            if (total > maxTotal) {
                maxTotal = total;
                ans[0] = l;
                ans[1] = mid;
                ans[2] = r;
            }
        }

        return ans;
    }
}