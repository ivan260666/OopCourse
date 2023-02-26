package ru.academits.shalnov.csv;

public class SpecialCharacterHandlerForHtml {
    public static void processText(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '&') {
                sb.replace(i, i + 1, "&amp;");
                i += "&amp;".length() - 1;
            }
        }

        while (sb.indexOf("<") != -1) {
            int index = sb.indexOf("<");

            sb.replace(index, index + 1, "&lt;");
        }

        while (sb.indexOf(">") != -1) {
            int index = sb.indexOf(">");

            sb.replace(index, index + 1, "&gt;");
        }

    }
}