package FileContentFilteringUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы аргументы.");
            return;
        }

        EnterOptions options = parseArguments(args);

        if (options == null) {
            return;
        }

        if (options.files.isEmpty()) {
            System.out.println("Ошибка: не переданы входные файлы.");
            return;
        }

        FileClassifier classifier = new FileClassifier();
        processFiles(options, classifier);

        if (!classifier.getIntegers().isEmpty()) {
            FileWriterUtil.write(options.outputPath + options.prefix + "integers.txt", classifier.getIntegers(), options.append);
        }

        if (!classifier.getFloats().isEmpty()) {
            FileWriterUtil.write(options.outputPath + options.prefix + "floats.txt", classifier.getFloats(), options.append);
        }

        if (!classifier.getStrings().isEmpty()) {
            FileWriterUtil.write(options.outputPath + options.prefix + "strings.txt", classifier.getStrings(), options.append);
        }

        if (options.fullStats) {
            classifier.printFullStats();
        } else if (options.shortStats) {
            classifier.printShortStats();
        }
        System.out.println("Сделано.");
    }

    private static EnterOptions parseArguments(String[] args) {
        EnterOptions options = new EnterOptions();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-a":
                    options.append = true;
                    break;

                case "-s":
                    options.shortStats = true;
                    break;

                case "-f":
                    options.fullStats = true;
                    break;

                case "-p":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        options.prefix = args[i + 1];
                        i++;
                    } else {
                        System.out.println("Ошибка: после -p нужно указать префикс.");
                        return null;
                    }
                    break;

                case "-o":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        options.outputPath = args[i + 1];
                        i++;
                    } else {
                        System.out.println("Ошибка: после -o нужно указать путь.");
                        return null;
                    }
                    break;

                default:
                    if (arg.startsWith("-")) {
                        System.out.println("Ошибка: Неизвестный параметр " + arg);
                        return null;
                    }
                    options.addFile(arg);
            }
        }

        return options;
    }

    private static void processFiles(EnterOptions options, FileClassifier classifier) {

        for (String fileName : options.files) {
            System.out.println("Чтение файла: " + fileName);

            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("Файл не найден: " + fileName);
                continue;
            }

            try (Scanner scanner = new Scanner(file)) {

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    classifier.processLine(line);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Ошибка чтения файла: " + fileName);
            }
        }
    }
}
