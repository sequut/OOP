package calculator;

import java.util.Stack;

public class Calculator {
    public static double calculate(String str){
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
}
