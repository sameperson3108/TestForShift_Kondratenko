package FileContentFilteringUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте какие-нибудь файлы формата .txt");
            return;
        }

        for (String fileName : args) {
            System.out.println("Чтение файла " + fileName);
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) System.out.println(scanner.nextLine());
                scanner.close();

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден " + fileName);
            }
        }
    }
}
