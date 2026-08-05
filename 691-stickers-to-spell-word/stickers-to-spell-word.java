import java.util.*;

class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] count = new int[n][26];

        // Frequency of each sticker
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                count[i][c - 'a']++;
            }
        }

        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        return dfs(target, count, memo);
    }

    private int dfs(String target, int[][] stickers, Map<String, Integer> memo) {
        if (memo.containsKey(target))
            return memo.get(target);

        int[] need = new int[26];
        for (char c : target.toCharArray()) {
            need[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int[] sticker : stickers) {

            // Optimization: sticker must contain first needed character
            if (sticker[target.charAt(0) - 'a'] == 0)
                continue;

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 26; i++) {
                int remain = need[i] - sticker[i];
                while (remain > 0) {
                    sb.append((char) ('a' + i));
                    remain--;
                }
            }

            String next = sb.toString();
            int temp = dfs(next, stickers, memo);

            if (temp != -1) {
                ans = Math.min(ans, temp + 1);
            }
        }

        memo.put(target, ans == Integer.MAX_VALUE ? -1 : ans);

        return memo.get(target);
    }
}