import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CreateJsonFileMetroMap createJsonFileMetroMap = new CreateJsonFileMetroMap();
        CreateJsonFileStationsInfo createJsonFileStationsInfo = new CreateJsonFileStationsInfo();
        String pathMetroMapInfo = "./DataCollector/data/MetroMapInfo.json";
        String pathStationsInfo = "./DataCollector/data/StationsInfo.json";

        JSONObject mapObject = createJsonFileMetroMap.create();
        JSONObject stationsObject = createJsonFileStationsInfo.create();
        writer(mapObject, pathMetroMapInfo);
        writer(stationsObject, pathStationsInfo);
    }

    public static void writer(JSONObject object, String path) {
        try {
            FileWriter file = new FileWriter(path);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
