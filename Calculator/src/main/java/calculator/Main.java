package calculator;

import java.util.Scanner;

public class Main extends calculator.Calculator {
     public static void main(String[] args){
         Scanner input = new Scanner(System.in);
         String expression;
         while (input.hasNextLine()){
             try{
                 expression = input.nextLine();
                 if (expression.equals("exit"))
                     break;

                 System.out.println(Calculator.calculate(expression));
             }
             catch (Exception e){
                 System.err.println(e.getMessage());
             }
         }
         input.close();
     }
}