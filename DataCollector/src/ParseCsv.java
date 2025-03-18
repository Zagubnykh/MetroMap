import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParseCsv {
    String folderPath = "./DataCollector/stations-data";

    private List<String> getCSVFile() {
        List<String> csvFile = new ArrayList<>();
        List<String> lines;
        FileSearch fileSearch = new FileSearch();
        String[] paths = fileSearch.getCSVFiles(folderPath).split("\n");
        for (String pathFile : paths) {
            try {
                lines = Files.readAllLines(Paths.get(pathFile));
                lines.remove(0);
                csvFile.addAll(lines);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return csvFile;
    }

    public HashSet<OpeningDateStation> getOpeningDatesStations() {
        HashSet<OpeningDateStation> allOpeningDateStations = new HashSet<>();
        List<String> lines = getCSVFile();
        for (String line : lines) {
            String[] fragments = line.split(",");
            if (fragments.length != 2) {
                System.out.println("Wrong line: " + line);
                continue;
            }
            String name = fragments[0].replaceAll("ё", "е");
            allOpeningDateStations.add(new OpeningDateStation(name, fragments[1]));
        }
        return allOpeningDateStations;
    }

}
