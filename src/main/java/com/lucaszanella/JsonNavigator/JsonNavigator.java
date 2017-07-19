package com.lucaszanella.JsonNavigator;

import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * Created by Lucas Zanella on 19/07/17.
 */
public class JsonNavigator {
    /*
        Given a path in the form "first_node.second_node.third_node...", it navigates through
        these nodes on jsonObject and get its final value: a String or Number (boolean and null
        will be added later). Example:
        Given this jsonObject:
        {
            "first_node": {
                "second_node": {
                    "third_node":"value"
                }
            }
        }

        The output of JsonValue Navigate("first_node.second_node.third_node", jsonObject) is "value"
     */
    public static JsonValue Navigate(String path, JsonObject jsonObject) {
        System.out.println("Navigating through " + path + ", jsonObject: " + jsonObject);
        JsonObject o = jsonObject;
        String[] nodes = path.split("\\."); //Do I really need to remove "" from the keys???
        for (String node: nodes) {
            node = node.replace("\"", "");
            JsonValue.ValueType current = o.get(node).getValueType();
            if (current.equals(JsonValue.ValueType.OBJECT)) {
                o = o.getJsonObject(node);
                System.out.println("is object, new o = " + o);
            } else if (current.equals(JsonValue.ValueType.STRING)) {
                return o.getJsonString(node);
            } else if (current.equals(JsonValue.ValueType.NUMBER)) {
                return o.getJsonNumber(node);
            } else if (current.equals(JsonValue.ValueType.ARRAY)) {
                //deal with this
            }

        }
        return null;
    }
}
