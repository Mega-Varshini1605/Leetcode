import java.util.*;

class Solution {

    private String exp;
    private int idx;
    private Map<String, Deque<Integer>> scope = new HashMap<>();

    public int evaluate(String expression) {
        exp = expression;
        idx = 0;
        return eval();
    }

    private int eval() {
        if (exp.charAt(idx) != '(') {
            // Integer
            if (exp.charAt(idx) == '-' || Character.isDigit(exp.charAt(idx))) {
                return parseInt();
            }

            // Variable
            String var = parseToken();
            return scope.get(var).peek();
        }

        idx++; // skip '('

        String op = parseToken();
        idx++; // skip space

        if (op.equals("add")) {
            int a = eval();
            idx++; // space
            int b = eval();
            idx++; // ')'
            return a + b;
        }

        if (op.equals("mult")) {
            int a = eval();
            idx++; // space
            int b = eval();
            idx++; // ')'
            return a * b;
        }

        // let
        List<String> assigned = new ArrayList<>();

        while (true) {
            if (exp.charAt(idx) == '(' ||
                exp.charAt(idx) == '-' ||
                Character.isDigit(exp.charAt(idx))) {

                int val = eval();

                for (String v : assigned)
                    scope.get(v).pop();

                idx++; // ')'
                return val;
            }

            int save = idx;
            String token = parseToken();

            if (exp.charAt(idx) == ')') {
                int val = scope.get(token).peek();

                for (String v : assigned)
                    scope.get(v).pop();

                idx++; // ')'
                return val;
            }

            idx++; // space

            if (exp.charAt(idx) == ')') {
                int val = scope.get(token).peek();

                for (String v : assigned)
                    scope.get(v).pop();

                idx++;
                return val;
            }

            // Check if token is final expression
            if (exp.charAt(save) == '(') {
                idx = save;
                int val = eval();

                for (String v : assigned)
                    scope.get(v).pop();

                idx++;
                return val;
            }

            String var = token;
            int value = eval();

            scope.computeIfAbsent(var, k -> new ArrayDeque<>()).push(value);
            assigned.add(var);

            if (exp.charAt(idx) == ' ')
                idx++;
        }
    }

    private int parseInt() {
        int sign = 1;

        if (exp.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }

        int num = 0;

        while (idx < exp.length() && Character.isDigit(exp.charAt(idx))) {
            num = num * 10 + (exp.charAt(idx) - '0');
            idx++;
        }

        return sign * num;
    }

    private String parseToken() {
        int start = idx;

        while (idx < exp.length() &&
               exp.charAt(idx) != ' ' &&
               exp.charAt(idx) != ')') {
            idx++;
        }

        return exp.substring(start, idx);
    }
}