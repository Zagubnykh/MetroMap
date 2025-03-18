import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParseWeb {
//js-metro-line
//data-line
    String path = "./DataCollector/data/code.html";

    private String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line).append("\n"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public List<MetroLine> getMetroLines() {
        String parseFile = parseFile(path);
        List<MetroLine> metroLines = new ArrayList<>();
        Document doc = Jsoup.parse(parseFile);
        Elements elements = doc.select("span.js-metro-line");
        elements.forEach(element -> {
            MetroLine metroLine = new MetroLine();
            metroLine.setName(element.text());
            metroLine.setNumber(element.attr("data-line"));
            metroLines.add(metroLine);
        });
        return metroLines;
    }

    public List<MetroStation> getMetroStations() {
        String parseFile = parseFile(path);
        List<MetroStation> metroStations = new ArrayList<>();
        Document doc = Jsoup.parse(parseFile);
        Elements stationsOnLine = doc.getElementsByClass("js-metro-stations");
        stationsOnLine.forEach(element -> {
            Elements stations = element.getElementsByClass("single-station");
            stations.forEach(station -> {
                MetroStation metroStation = new MetroStation();
                String name = station.getElementsByClass("name").text().replaceAll("ё", "е");
                String line = element.attr("data-line");
                Boolean hasConnection = station.select("span.t-icon-metroln").hasAttr("title");
                metroStation.setName(name);
                metroStation.setLineNumber(line);
                metroStation.setHasConnection(hasConnection);
                metroStations.add(metroStation);
            });
        });
        return metroStations;
    }
}
