import java.util.*;

class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        int n = code.length();

        while (i < n) {
            // Everything must be inside a root tag
            if (i > 0 && stack.isEmpty()) return false;

            if (code.startsWith("<![CDATA[", i)) {
                // CDATA
                if (stack.isEmpty()) return false;

                int j = code.indexOf("]]>", i);
                if (j == -1) return false;

                i = j + 3;
            } else if (code.startsWith("</", i)) {
                // Closing tag
                int j = code.indexOf(">", i);
                if (j == -1) return false;

                String tag = code.substring(i + 2, j);

                if (!isValidTag(tag)) return false;
                if (stack.isEmpty() || !stack.pop().equals(tag))
                    return false;

                i = j + 1;

                // After root closes, nothing should remain
                if (stack.isEmpty() && i != n)
                    return false;

            } else if (code.startsWith("<", i)) {
                // Opening tag
                int j = code.indexOf(">", i);
                if (j == -1) return false;

                String tag = code.substring(i + 1, j);

                if (!isValidTag(tag)) return false;

                stack.push(tag);
                i = j + 1;

            } else {
                // Normal character
                i++;
            }
        }

        return stack.isEmpty();
    }

    private boolean isValidTag(String tag) {
        if (tag.length() < 1 || tag.length() > 9)
            return false;

        for (char c : tag.toCharArray()) {
            if (c < 'A' || c > 'Z')
                return false;
        }

        return true;
    }
}