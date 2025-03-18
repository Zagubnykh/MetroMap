import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.print.SimpleDoc;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ParseJson {
    String folderPath = "./DataCollector/stations-data";

    private String recurringStationName1 = "Смоленская";
    private String recurringStationName2 = "Арбатская";

    private List<String> getJSONFile() {
        //StringBuilder jsonFile = new StringBuilder();
        List<String> jsonFile = new ArrayList<>();
        FileSearch fileSearch = new FileSearch();
        String[] paths = fileSearch.getJSONFiles(folderPath).split("\n");
        for (String pathFile : paths) {
            try {
                jsonFile.add(Files.readString(Paths.get(pathFile)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jsonFile;
    }

    private HashSet<MetroStationDepth> getRawStationsDepths() {
        HashSet<MetroStationDepth> allMetroStationsDepths = new HashSet<>();
        JSONParser jsonParser = new JSONParser();
        try {
            for (String jsonArrays : getJSONFile()) {
                JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonArrays);
                for (Object stationDepthObject : jsonArray) {
                    JSONObject stationDepth = (JSONObject) stationDepthObject;
                    String rawStationName = (String) stationDepth.get("station_name");
                    String stationName = rawStationName.replaceAll("ё", "е");
                    String rawDepth = (String) stationDepth.get("depth");
                    String depth = rawDepth.replaceAll(",", ".").replace("?", "0");
                    allMetroStationsDepths.add(new MetroStationDepth(stationName, depth));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allMetroStationsDepths;
    }

    public HashSet<MetroStationDepth> getStationsDepths() {
        List<MetroStationDepth> rawMetroStationDepths = new ArrayList<>(getRawStationsDepths());
        for (int i = 0; i < rawMetroStationDepths.size(); i++) {
            String name1 = rawMetroStationDepths.get(i).getStationName();
            double depth1 = Double.parseDouble(rawMetroStationDepths.get(i).getStationDepth());
            for (int j = 0; j < rawMetroStationDepths.size(); j++) {
                if (i == j) {
                    continue;
                }
                String name2 = rawMetroStationDepths.get(j).getStationName();
                double depth2 = Double.parseDouble(rawMetroStationDepths.get(j).getStationDepth());
                if (name1.equals(name2) &&
                        !name1.equals(recurringStationName1) &&
                        !name1.equals(recurringStationName2)) {
                    if (depth1 > depth2) {
                        rawMetroStationDepths.get(i).setStationDepth(Double.toString(depth2));
                    } else {
                        rawMetroStationDepths.get(j).setStationDepth(Double.toString(depth1));
                    }
                }
            }
        }
        return new HashSet<>(rawMetroStationDepths);
    }
}
