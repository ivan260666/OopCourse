package ru.academits.shalnov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            StringBuilder currentLine = new StringBuilder();
            StringBuilder result = new StringBuilder("<table>");

            boolean isCellInsideQuotes = false;
            int quotesCount = 0;
            int lastQuoteIndex = 0;

            while (scanner.hasNextLine()) {
                if (!isCellInsideQuotes) {
                    result.append("<tr><td>");
                }

                currentLine.append(scanner.nextLine());

                SpecialCharacterHandlerForHtml.processText(currentLine);

                for (int i = 0; i < currentLine.length(); i++) {
                    if (currentLine.charAt(i) == '"' || isCellInsideQuotes) {
                        if (!isCellInsideQuotes) {
                            currentLine.delete(i, i + 1);
                            quotesCount = 1;
                        }

                        isCellInsideQuotes = true;

                        for (int k = i; k < currentLine.length(); k++) {
                            if (currentLine.charAt(k) == '"') {
                                quotesCount++;
                                lastQuoteIndex = k;
                            }

                            if (quotesCount % 2 == 0) {
                                if (currentLine.charAt(k) == ',') {
                                    isCellInsideQuotes = false;

                                    currentLine.delete(lastQuoteIndex, lastQuoteIndex + 1);
                                    i = k - 1;

                                    result.append(currentLine.substring(0, i));
                                    result.append("</td><td>");

                                    currentLine.delete(0, i + 1);
                                    i = -1;

                                    break;
                                }

                                if (k + 1 == currentLine.length()) {
                                    isCellInsideQuotes = false;

                                    currentLine.delete(lastQuoteIndex, lastQuoteIndex + 1);
                                    i = k - 1;

                                    break;
                                }
                            }
                        }
                    } else if (currentLine.charAt(i) == ',') {
                        result.append(currentLine.substring(0, i));
                        result.append("</td><td>");

                        currentLine.delete(0, i + 1);
                        i = -1;
                    }
                }

                result.append(currentLine);

                if (!isCellInsideQuotes) {
                    result.append("</td></tr>");
                } else {
                    result.append("<br/>");
                }

                currentLine.delete(0, currentLine.length());
            }

            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == '"') {
                    result.delete(i, i + 1);
                }
            }

            result.append("</table>");
            writer.println(result);
        }
    }
}