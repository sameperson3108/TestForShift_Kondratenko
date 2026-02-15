package FileContentFilteringUtility;

import java.util.ArrayList;
import java.util.List;

public class FileClassifier {

    private List<String> integers = new ArrayList<>();
    private List<String> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

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
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return s.contains(".");
        } catch (Exception e) {
            return false;
        }
    }

    public void printResults() {

        System.out.println("=== Integers ===");
        for (String s : integers) {
            System.out.println(s);
        }

        System.out.println("=== Floats ===");
        for (String s : floats) {
            System.out.println(s);
        }

        System.out.println("=== Strings ===");
        for (String s : strings) {
            System.out.println(s);
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
}

