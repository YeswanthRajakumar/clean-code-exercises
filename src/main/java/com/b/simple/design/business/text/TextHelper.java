package com.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
        if (str.length() < 2) return str;
        char[] strArray = str.toCharArray();
        char lastChar = strArray[strArray.length - 1];
        char secondLastChar = strArray[strArray.length - 2];
        strArray[strArray.length - 1] = secondLastChar;
        strArray[strArray.length - 2] = lastChar;
        return new String(strArray);
    }

    public String truncateAInFirst2Positions(String str) {
        if (str.length() < 3) return "";
        StringBuilder s = new StringBuilder(str);
        if (s.charAt(0) == 'A') s.replace(0, 1, "");
        if (s.charAt(0) == 'A') s.replace(0, 1, "");
        if (s.charAt(1) == 'A') s.replace(1, 2, "");
        return s.toString();

    }
}

