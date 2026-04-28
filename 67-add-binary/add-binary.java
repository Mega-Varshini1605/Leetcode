class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1; // pointer for string a
        int j = b.length() - 1; // pointer for string b
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry; // carry-a muthala add pannikkanum
            
            if (i >= 0) {
                sum += a.charAt(i) - '0'; // converting char to int
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            res.append(sum % 2); // binary sum (0 or 1)
            carry = sum / 2;     // carry calculation
        }
        
        return res.reverse().toString(); // reverse panna correct answer kedaikkum
    }
}
