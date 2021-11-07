package calculator;

import java.util.Scanner;

public class Main extends calculator.Calculator {
     public static void main(String[] args){
         Scanner input = new Scanner(System.in);
         String expression;
         String answer;

         System.out.println("please choose which numbers you want to work with(type real/complex)");
         answer = input.nextLine();
         if (answer.equals("complex"))
             System.out.println("please type number like that " + "'a +/-bi'");

         while (input.hasNextLine()){
             try{
                 expression = input.nextLine();
                 if (expression.equals("exit"))
                     break;

                 switch (answer){
                     case "real" -> System.out.println(Calculator.calculateInReal(expression));
                     case "complex" -> System.out.println(Calculator.calculateInComplex(expression));
                     default -> throw new Exception("no such type");
                 }
             }
             catch (Exception e){
                 System.err.println(e.getMessage());
             }
         }
         input.close();
     }
}