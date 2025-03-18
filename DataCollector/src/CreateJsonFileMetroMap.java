import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class CreateJsonFileMetroMap {
    ParseWeb parseWeb = new ParseWeb();
    private List<MetroStation> metroStations = parseWeb.getMetroStations();
    private List<MetroLine> metroLines = parseWeb.getMetroLines();


    public JSONObject create() {
        JSONObject mainObject = new JSONObject();
        String keyStations = "stations";
        String keyLines = "lines";

        JSONObject metroStationsObject = new JSONObject();
        metroLines.forEach(metroLine -> {
            JSONArray stationsArray = new JSONArray();
            String listStations = getStationsOnLine().get(metroLine.getNumber());
            String[] fragments = listStations.split(";");
            stationsArray.addAll(Arrays.asList(fragments));
            metroStationsObject.put(metroLine.getNumber(), stationsArray);
        });

        JSONArray metroLinesArray = new JSONArray();
        metroLines.forEach(metroLine -> {
            JSONObject object = new JSONObject();
            object.put("number", metroLine.getNumber());
            object.put("name", metroLine.getName());
            metroLinesArray.add(object);
        });

        mainObject.put(keyStations, metroStationsObject);
        mainObject.put(keyLines, metroLinesArray);
        return mainObject;

    }

    private Map<String, String> getStationsOnLine() {
        Map<String, String> stationsOnLine = new HashMap<>();
        Map<String, List<MetroStation>> map = metroStations.stream()
                .collect(Collectors.groupingBy(MetroStation::getLineNumber));
        for (Map.Entry<String, List<MetroStation>> item : map.entrySet()) {
            StringBuilder builder = new StringBuilder();
            for (MetroStation metroStation : item.getValue()) {
                builder.append(metroStation.getName()).append(";");
            }
            stationsOnLine.put(item.getKey(), builder.toString());
        }
        return stationsOnLine;
    }
}
