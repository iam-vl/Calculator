package com.calculator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your expression");
        String rawString = sc.nextLine();
        String resultMain = calc(rawString);
        System.out.println("Result: " + resultMain);
    }

    static String calc(String rawInput) throws IOException {

        Equation eq = new Equation(Equation.defineOpCode(rawInput));
        String[] memberArr = Equation.parseExpression(rawInput);
        String memberValidity = Equation.validateArray(memberArr);
        int[] intNumbers = new int[2];
        if ((memberValidity == "roman") || (memberValidity == "integer")) {
            eq.membersValid = true;
            convertNumbersAndCalculate(eq, memberValidity, memberArr);
        } else if (memberValidity == "mismatch") {
            eq.membersValid = false;
            throw new IOException("Формат аргументов не совпадает");
        }

        String[] outArr = new String[1];

        if (eq.isRoman && eq.membersValid) {
            outArr[0] =  Converter.convertIntToRoman(eq.result);
        } else if (eq.membersValid) {
            outArr[0] =  Integer.toString(eq.result);
        }
        return outArr[0];
    }


    static void convertNumbersAndCalculate(Equation eq, String memberValidity, String[] stringArr) throws IOException {

        int[] intNumbers = {1, 2};
        if (memberValidity == "roman") {
            eq.isRoman = true;
            eq.leftV = Converter.convertRomanToNumber(stringArr[0]);
            eq.rightV = Converter.convertRomanToNumber(stringArr[1]);
        } else if (memberValidity == "integer") {
            eq.isRoman = false;
            eq.setLeftV(Integer.parseInt(stringArr[0]));
            eq.setRightV(Integer.parseInt(stringArr[1]));
        }
        eq.execute();
    }
}
