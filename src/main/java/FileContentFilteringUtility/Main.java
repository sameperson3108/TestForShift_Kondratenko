package FileContentFilteringUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> integers = new ArrayList<>();
        List<String> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();


        if (args.length == 0) {
            System.out.println("Передайте какие-нибудь файлы формата .txt");
            return;
        }

        for (String fileName : args) {
            System.out.println();
            System.out.println("Чтение файла " + fileName);
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (isInteger(line)) integers.add(line);
                    else if (isDouble(line)) floats.add(line);
                    else strings.add(line);
                }

                scanner.close();

                System.out.println("-Integers-");
                for (String s : integers) {
                    System.out.println(s);
                }

                System.out.println("-Floats-");
                for (String s : floats) {
                    System.out.println(s);
                }

                System.out.println("-Strings-");
                for (String s : strings) {
                    System.out.println(s);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + fileName);
            }
        }
    }

    public static boolean isInteger(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String line) {
        try {
            Double.parseDouble(line);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
