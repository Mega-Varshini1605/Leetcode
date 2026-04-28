class Solution {
    public int[] plusOne(int[] digits) {
        // Kadaisi digit la irundhu check pannanum
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // Increment panna odane return pannidalam
            }
            // 9 ah irundha 0 nu maathum
            digits[i] = 0;
        }

        // Ellame 9 ah irundha (e.g., 999 -> 1000), array length 1 athigamaagum
        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1; // First digit matum 1, mathathellam default ah 0
        return newNumber;
    }
}
