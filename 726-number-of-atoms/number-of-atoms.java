import java.util.*;

class Solution {
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new TreeMap<>());

        int i = 0;
        int n = formula.length();

        while (i < n) {
            char c = formula.charAt(i);

            if (c == '(') {
                stack.push(new TreeMap<>());
                i++;
            } 
            else if (c == ')') {
                i++;

                int start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int mult = (start < i) ? Integer.parseInt(formula.substring(start, i)) : 1;

                Map<String, Integer> top = stack.pop();
                Map<String, Integer> curr = stack.peek();

                for (String atom : top.keySet()) {
                    curr.put(atom, curr.getOrDefault(atom, 0) + top.get(atom) * mult);
                }
            } 
            else {
                // Parse atom name
                int start = i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }

                String atom = formula.substring(start, i);

                // Parse count
                start = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int count = (start < i) ? Integer.parseInt(formula.substring(start, i)) : 1;

                Map<String, Integer> curr = stack.peek();
                curr.put(atom, curr.getOrDefault(atom, 0) + count);
            }
        }

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> result = stack.pop();

        for (String atom : result.keySet()) {
            sb.append(atom);
            if (result.get(atom) > 1) {
                sb.append(result.get(atom));
            }
        }

        return sb.toString();
    }
}