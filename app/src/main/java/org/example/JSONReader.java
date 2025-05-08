package org.example;
// import hashmap
import java.util.HashMap;
// import gson
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class JSONReader {
    
    private String readFile(int status) {
        // check if data.json exists, otherwise create it
        String fileName = null;
        if (status == 1) {
            fileName = "data.json";
            if (!new java.io.File(fileName).exists()) {
                try {
                    // create new file if it doesn't exist
                    new java.io.File(fileName).createNewFile();
                    return null;
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (status == 0) {
            fileName = "testData.json";
            // check if testData.json exists, otherwise create it
            if (!new java.io.File(fileName).exists()) {
                try {
                    // create new file if it doesn't exist
                    new java.io.File(fileName).createNewFile();
                    return null;
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // read the file
        StringBuilder jsonString = new StringBuilder();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }


    public HashMap<String, Entry> readJSON(int status) {
        // 0 for testData.json, 1 for data.json
        // specify the type of hashMap
        Type type = new TypeToken<HashMap<String, Entry>>() {}.getType();


        // take in from readFile()
        String jsonString = readFile(status);

        // if the file is empty/or is a new file, return an empty HashMap as there is no data
        if (jsonString == null || jsonString.isEmpty()) {
            return new HashMap<>();
        }

        // use Gson to parse the json string into a HashMap
        Gson gson = new GsonBuilder().create();
        HashMap<String, Entry> entryMap = gson.fromJson(jsonString, type);
        return entryMap;
    }

    public void writeJSON(HashMap<String, Entry> entryMap) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(entryMap);
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("data.json"))) {
            bw.write(jsonString);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
 
}
