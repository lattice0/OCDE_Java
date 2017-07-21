package com.lucaszanella.JsonUtilities;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
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

    public static JsonValue Navigate(String path, JsonStructure jsonStructure) {
        System.out.println("Navigating through " + path);
        JsonValue current = jsonStructure;
        String[] nodes = path.split("\\.");
        for (String node: nodes) {
            System.out.println("node: " + node);
            if (current.getValueType().equals(JsonValue.ValueType.OBJECT)) {
                current = ((JsonObject) current).get(node);
                System.out.println("is object, new o = " + current);
            } else
            if (current.getValueType().equals(JsonValue.ValueType.ARRAY)) {
                System.out.println("parsing " + node + " to number");
                current = ((JsonArray) current).get(Integer.parseInt(node));
                System.out.println("is array, new o = " + current);
            }
        }
        JsonValue.ValueType t = current.getValueType();
        if (t.equals(JsonValue.ValueType.NUMBER) || t.equals(JsonValue.ValueType.STRING) ||
            t.equals(JsonValue.ValueType.TRUE)   || t.equals(JsonValue.ValueType.FALSE)) {
            return current;
        } else {
            //Return if did not reach a literal or throw exception? I'll let you decide
            return current;
        }
    }
}
