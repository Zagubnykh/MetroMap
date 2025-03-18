import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@SuppressWarnings("unchecked")
public class CreateJsonFileStationsInfo {
    ParseWeb parseWeb = new ParseWeb();
    ParseJson parseJson = new ParseJson();
    ParseCsv parseCsv = new ParseCsv();

    List<MetroStation> metroStationsList = parseWeb.getMetroStations();
    List<MetroLine> metroLinesList = parseWeb.getMetroLines();
    List<OpeningDateStation> openingDateStationsList = new ArrayList<>(parseCsv.getOpeningDatesStations());
    List<MetroStationDepth> metroStationDepthList = new ArrayList<>(parseJson.getStationsDepths());

    public JSONObject create() {
        JSONObject mainObject = new JSONObject();
        String keyStations = "stations";

        JSONArray stationsInfoArray = new JSONArray();
        for (MetroStation metroStation : metroStationsList) {
            JSONObject stationObject = new JSONObject();
            String nameStation = metroStation.getName();
            stationObject.put("name", nameStation);
            for (MetroLine metroLine : metroLinesList) {
                if (metroStation.getLineNumber().equalsIgnoreCase(metroLine.getNumber())) {
                    String lineNumber = metroLine.getNumber();
                    stationObject.put("line", lineNumber);
                }
            }

            for (OpeningDateStation openingDateStation : openingDateStationsList) {
                if (metroStation.getName().equalsIgnoreCase(openingDateStation.getStationName())) {
                    String openingDate = openingDateStation.getOpeningDate();
                    stationObject.put("date", openingDate);
                }
            }

            for (MetroStationDepth metroStationDepth : metroStationDepthList) {
                if (metroStation.getName().equalsIgnoreCase(metroStationDepth.getStationName())) {
                    String depth = metroStationDepth.getStationDepth();
                    stationObject.put("depth", depth);
                }
            }

            stationObject.put("hasConnection", metroStation.getHasConnection());
            stationsInfoArray.add(stationObject);
        }
        mainObject.put(keyStations, stationsInfoArray);
        return mainObject;
    }

}
