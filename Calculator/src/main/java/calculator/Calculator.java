package calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    public static double calculateInReal(String str){
        String[] words = str.split(" ");
        Stack<Double> que = new Stack<>();

        for (int i = words.length - 1; i >= 0; i--){
            try {
                que.push(Double.parseDouble(words[i]));
            }
            catch (NumberFormatException e){
                switch (words[i]){
                    case "+" -> que.push(que.pop() + que.pop());
                    case "-" -> que.push(que.pop() - que.pop());
                    case "/" -> que.push(que.pop() / que.pop());
                    case "*" -> que.push(que.pop() * que.pop());
                    case "log" -> que.push(Math.log(que.pop()));
                    case "sin" -> que.push(Math.sin(que.pop()));
                    case "cos" -> que.push(Math.cos(que.pop()));
                    case "pow" -> que.push(Math.pow(que.pop(), que.pop()));
                    case "sqrt" -> que.push(Math.sqrt(que.pop()));
                    default -> throw new RuntimeException("Invalid token: " + words[i]);
                }
            }
        }
        return que.pop();
    }


    public static String calculateInComplex(String str) throws Exception {
        String[] words = str.split(" ");

        int first = 0;
        ArrayList<Character> operation = new ArrayList<>();

        Complex c1 = new Complex();
        Complex c2 = new Complex();

        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].contains("i")) {
                double Im;
                try{
                    if (words[i].charAt(0) == '+')
                        Im = Double.parseDouble(words[i].substring(1, words[i].length() - 2));
                    else
                        Im = Double.parseDouble(words[i].substring(0, words[i].length() - 2));
                }
                catch (NumberFormatException e) {
                    throw new Exception("Invalid format");
                }
                if (first == 0)
                    c2.setIm(Im);
                else
                    c1.setIm(Im);
            } else {
                try {
                    double Re;
                    Re = Double.parseDouble(words[i]);
                    if (first == 0)
                        c2.setRe(Re);
                    else
                        c1.setRe(Re);
                }
                catch (NumberFormatException e) {
                    switch (words[i]) {
                        case "+", "*", "-", "/" -> operation.add(words[i].charAt(0));
                        default -> throw new RuntimeException("Invalid token: " + words[i]);
                    }
                    first += 1;
                }
            }
        }
        switch (operation.get(0)){
            case '+' -> c1.add(c2);
            case '-' -> c1.sub(c2);
            case '*' -> c1.multiply(c2);
            case '/' -> c1.divide(c2);
            default -> throw new RuntimeException("Invalid token: " + operation.get(0));
        }

        return c1.makeString();
    }
}