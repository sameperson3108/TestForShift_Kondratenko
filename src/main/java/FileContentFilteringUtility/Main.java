package FileContentFilteringUtility;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean append = false;
        String prefix = "result";

        if (args.length == 0) {
            System.out.println("Передайте имена файлов");
            return;
        }

        FileClassifier classifier = new FileClassifier();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-a")) {
                append = true;
                continue;
            }

            if (arg.equals("-p")) {
                if (i + 1 < args.length) {
                    prefix = args[i + 1];
                    i++;
                }
                continue;
            }

            System.out.println("Читаем файл: " + arg);
            try {
                File file = new File(arg);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    classifier.processLine(line);
                }

                scanner.close();

            } catch (Exception e) {
                System.out.println("Не удалось прочитать файл: " + arg);
            }
        }

        // вывод в консоль
        classifier.printResults();

        // запись в файлы
        FileWriterUtil.write(prefix + "integers.txt", classifier.getIntegers(), append);
        FileWriterUtil.write(prefix + "floats.txt", classifier.getFloats(), append);
        FileWriterUtil.write(prefix +"strings.txt", classifier.getStrings(), append);
    }
}
