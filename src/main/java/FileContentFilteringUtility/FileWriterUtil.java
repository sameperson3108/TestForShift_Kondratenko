package FileContentFilteringUtility;

import java.util.List;
import java.io.FileWriter;

public class FileWriterUtil {

    public static void write(String fileName, List<String> data, boolean append) {
        try {
            FileWriter writer = new FileWriter(fileName, append);
            for (String line : data) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл: " + fileName);
        }
    }
}
