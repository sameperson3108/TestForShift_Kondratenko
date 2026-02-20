package FileContentFilteringUtility;

import java.util.ArrayList;
import java.util.List;

public class EnterOptions {
    public boolean append = false;
    public boolean shortStats = false;
    public boolean fullStats = false;
    public String prefix = "";
    public String outputPath = "";

    public List<String> files = new ArrayList<>();

    public void addFile(String fileName) {
        files.add(fileName);
    }
}
