package com.lucaszanella.JsonUtilities;

import javax.json.Json;
import javax.json.JsonStructure;
import java.io.FileReader;
import java.io.StringReader;

/**
 * Created by lucas on 20/07/17.
 */
public class JsonReader {
    public static JsonStructure ReadFile(String path) throws Exception{
        javax.json.JsonReader jsonReader = Json.createReader(new FileReader(path));
        return jsonReader.read();
    }

    public static JsonStructure ReadString(String string) throws Exception{
        javax.json.JsonReader jsonReader = Json.createReader(new StringReader(string));
        return jsonReader.read();
    }
}
