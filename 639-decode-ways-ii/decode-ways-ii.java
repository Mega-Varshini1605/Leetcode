class Solution {
    static final int MOD = 1_000_000_007;

    public int numDecodings(String s) {
        long prev2 = 1;
        long prev1 = ways1(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            long curr = (ways1(s.charAt(i)) * prev1) % MOD;
            curr = (curr + ways2(s.charAt(i - 1), s.charAt(i)) * prev2) % MOD;

            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }

    // Ways to decode one character
    private int ways1(char c) {
        if (c == '*') return 9;
        if (c == '0') return 0;
        return 1;
    }

    // Ways to decode two characters
    private int ways2(char c1, char c2) {
        if (c1 == '*' && c2 == '*') {
            return 15; // 11-19 and 21-26
        }

        if (c1 == '*') {
            if (c2 >= '0' && c2 <= '6')
                return 2; // 1c2 or 2c2
            else
                return 1; // only 1c2
        }

        if (c2 == '*') {
            if (c1 == '1')
                return 9;
            if (c1 == '2')
                return 6;
            return 0;
        }

        int num = (c1 - '0') * 10 + (c2 - '0');
        return (num >= 10 && num <= 26) ? 1 : 0;
    }
}