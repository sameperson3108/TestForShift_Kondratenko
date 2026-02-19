package FileContentFilteringUtility;

import java.util.ArrayList;
import java.util.List;

public class FileClassifier {

    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void processLine(String line) {

        if (isInteger(line)) {
            integers.add(line);
        } else if (isDouble(line)) {
            floats.add(line);
        } else {
            strings.add(line);
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return s.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getFloats() {
        return floats;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void printShortStats() {
        System.out.println("- Краткая статистика -");
        System.out.println("Integers: " + integers.size());
        System.out.println("Floats: " + floats.size());
        System.out.println("Strings: " + strings.size());
    }

    public void printFullStats() {
        System.out.println("- Полная статистика -");

        if (!integers.isEmpty()) {
            int min = Integer.parseInt(integers.get(0));
            int max = min;
            long sum = 0;

            for (String str : integers) {
                int value = Integer.parseInt(str);

                if (value < min) min = value;
                if (value > max) max = value;

                sum += value;
            }
            System.out.println("Integers: ");
            System.out.println("Count: " + integers.size());
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Average: " + (sum / (double)integers.size()));
        }

        if (!floats.isEmpty()) {
            double min = Double.parseDouble(floats.get(0));
            double max = min;
            double sum = 0;

            for (String str : floats) {
                double value = Double.parseDouble(str);

                if (value < min) min = value;
                if (value > max) max = value;

                sum += value;
            }
            System.out.println("Floats: ");
            System.out.println("Count: " + floats.size());
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Average: " + (sum / floats.size()));
        }

        if (!strings.isEmpty()) {
            int minLength = strings.get(0).length();
            int maxLength = minLength;

            for (String str : strings) {
                int length = str.length();

                if (length < minLength) minLength = length;
                if (length > maxLength) maxLength = length;
            }

            System.out.println("Strings: ");
            System.out.println("Count: " + strings.size());
            System.out.println("min: " + minLength);
            System.out.println("max: " + maxLength);
        }
    }
}

