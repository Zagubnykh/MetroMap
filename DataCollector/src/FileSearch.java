import java.io.File;
import java.io.PrintWriter;

public class FileSearch {

    public String getCSVFiles (String folderPath) {
        StringBuilder absolutePathCSVFiles = new StringBuilder();
        File folder = new File(folderPath);
        if (folder.isFile() && folder.getName().endsWith(".csv")) {
            absolutePathCSVFiles.append(folder.getAbsolutePath()).append("\n");
            return absolutePathCSVFiles.toString();
        }
        try {
            File[] files = folder.listFiles();
            for (File file : files) {
                absolutePathCSVFiles.append(getCSVFiles(file.getAbsolutePath()));
            }
        } catch (NullPointerException ex) {
            ex.fillInStackTrace();
        }
        return absolutePathCSVFiles.toString();
    }

    public String getJSONFiles(String folderPath) {
        StringBuilder absolutePathJSONFiles = new StringBuilder();
        File folder = new File(folderPath);
        if (folder.isFile() && folder.getName().endsWith(".json")) {
            absolutePathJSONFiles.append(folder.getAbsolutePath()).append("\n");
            return absolutePathJSONFiles.toString();
        }
        try {
            File[] files = folder.listFiles();
            for (File file : files) {
                absolutePathJSONFiles.append(getJSONFiles(file.getAbsolutePath()));
            }
        } catch (NullPointerException ex) {
            ex.fillInStackTrace();
        }
        return absolutePathJSONFiles.toString();
    }
}
