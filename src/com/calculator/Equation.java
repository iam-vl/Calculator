package com.calculator;

import java.io.IOException;

class Equation {
    String opCode;
    int leftV;
    int rightV;
    int result;
    boolean isRoman;
    boolean membersValid;

    public void setLeftV(int leftV) throws IOException {
        if (leftV > 10)
            throw new IOException("Значение слева больше 10.");
        else
            this.leftV = leftV;
    }

    public void setRightV(int rightV) throws IOException {
        if (rightV > 10)
            throw new IOException("Значение справа больше 10.");
        else
            this.rightV = rightV;
    }

    Equation() {}
    Equation(String opCode) {this.opCode = opCode; }

    static String defineOpCode(String originalExpression) throws IOException {
        // Clean all spaces
        String expression2 = originalExpression.replaceAll("\\s", "");
        String code;
        if (expression2.contains("+"))
            code = "a";
        else if (expression2.contains("-"))
            code = "s";
        else if (expression2.contains("*"))
            code = "m";
        else if (expression2.contains("/"))
            code = "d";
        else
            throw new IOException("Строка не является математической операцией");
        return code;
    }

    static String[] parseExpression(String rawInput) throws IOException {
        String eqString = rawInput.replaceAll("\\s", "");

        String[] eqArray = eqString.split("[*/+-]");
        String leftString = eqArray[0];
        String rightString = eqArray[1];

        if (eqArray.length != 2) {
            throw new IOException("Неверное число аргументов (не два)");
        }
        // Parsed, but not validated array
        String[] parsedArray = {leftString, rightString};
        return parsedArray;
    }

    static String validateArray(String[] parsedArray) throws IOException {
        String memberValidity = "unknown/invalid";
        Converter conv = new Converter();
        boolean isRomanLeft, isRomanRight, isValidNumLeft, isValidNumRight;
        // System.out.println("Debug3");
        isRomanLeft = conv.romanMap.containsKey(parsedArray[0]);
        isRomanRight = conv.romanMap.containsKey(parsedArray[1]);
        // System.out.println("Debug4");
        isValidNumLeft = isInt(parsedArray[0]);
        isValidNumRight = isInt(parsedArray[1]);
        // System.out.println("Debug5");

        // if ((conv.romanMap.containsKey(parsedArray[0])) && (conv.romanMap.containsKey(parsedArray[0])) ) {
        if ( isRomanLeft && isRomanRight ) {
            memberValidity = "roman";
        // } else if ((isInt(parsedArray[0])) && (isInt(parsedArray[0]))) {
        } else if ( isValidNumLeft && isValidNumRight ) {
            memberValidity = "integer";
        } else if ((isRomanLeft && isValidNumRight) || (isValidNumLeft && isRomanRight)) {
            memberValidity = "mismatch";
        } else {
            throw new IOException("Некорректная математическая операция");
        }
        return memberValidity;
    }

    static boolean isInt(String s) throws IOException {
        try {
            int i = Integer.parseInt(s);
            if ((i > 10) && (i <= 0)) {
                throw new IOException("Недопустимое число");
            }
            return true;
        } catch(Exception e) {
            // System.out.println(e);
            return false;
        }
    }

    void execute() throws IOException {
        switch (opCode) {
            case "a":
                result = leftV + rightV;
                break;
            case "s":
                result = leftV - rightV;
                break;
            case "m":
                result = leftV * rightV;
                break;
            case "d":
                if (rightV != 0 ) {
                    result = leftV / rightV;
                    break;
                } else {
                    throw new IOException("Неверная операция: деление на нуль. ");
                }

            default:
                System.out.println("Некорректная операция. ");
//                System.out.println("Invalid opCode: " + opCode);
//                result = 0;
//                break;
        }
    }
}
