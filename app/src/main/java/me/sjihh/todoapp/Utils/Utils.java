package me.sjihh.todoapp.Utils;

public class Utils {

    public static Long getLongFromString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        try {
            return Long.parseLong(str.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer getIntFromString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
