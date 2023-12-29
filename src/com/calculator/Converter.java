package com.calculator;

import java.io.IOException;
import java.util.HashMap;

class Converter {
    static String[] romanNumerals = {
            "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"
    };
    HashMap<String, Integer> romanMap = new HashMap<>();

    Converter() {
        populateHashMap(romanMap);
    }

    static void populateHashMap(HashMap<String, Integer> hm) {
        for (int i = 0; i < romanNumerals.length; i++) {
            hm.put(romanNumerals[i], i + 1);
        }
    }

    static int convertRomanToNumber(String s) throws NumberFormatException {
        // We only accept values 1 thru 10.

        HashMap<String, Integer> romanNumeralMap = new HashMap<>();
        for (int i = 0; i < romanNumerals.length; i++) {
            romanNumeralMap.put(romanNumerals[i], i + 1);
        }
        if (romanNumeralMap.containsKey(s)) {
            return romanNumeralMap.get(s);
        } else {
            // throw new IOException("This isn't a valid Roman numeral input value.");
            throw new NumberFormatException("This isn't a valid Roman numeral input value.");
        }
    }

    static String[] romanNums = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    static int[] ints = new int[] {100, 90, 50, 40, 10, 9, 5, 4, 1 };

    static String convertIntToRoman(int number) {
        // Range: 1...100 (I...C)
        String romanNum;
        StringBuilder sb = new StringBuilder(10);

        int i = 0;
        while (number > 0) {
            while (number >= ints[i]) {
                sb.append(romanNums[i]);
                number -= ints[i];
            } i++;
        }
        romanNum = sb.toString();
        return romanNum;
    }
}
