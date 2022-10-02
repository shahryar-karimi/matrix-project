package utils;

import java.util.regex.Pattern;

public class NumbersProtocol {
    public static boolean isUnsignedInteger(String number) {
        String regex = "\\d+";
        return Pattern.matches(regex, number);
    }

    public static boolean isDouble(String number) {
        String regex = "([-+])?\\d*\\.?\\d+";
        return Pattern.matches(regex, number);
    }
}
