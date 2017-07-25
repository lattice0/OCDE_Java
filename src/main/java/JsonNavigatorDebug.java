import com.lucaszanella.JsonUtilities.JsonNavigator;
import com.lucaszanella.JsonUtilities.JsonReader;

import javax.json.JsonStructure;
import javax.json.JsonValue;

public class JsonNavigatorDebug {
    public static void main(String[] args) {
        try {
            String j = "{\n" +
                    "\t\"a\": {\n" +
                    "\t\t\"b\": {\n" +
                    "\t\t\t\"c\": \"d\"\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\t\"e\": \"f\",\n" +
                    "\t\"g\": {\n" +
                    "\t\t\"h\": \"i\"\n" +
                    "\t},\n" +
                    "\t\"j\": {\n" +
                    "\t\t\"k\": [\"a\", \"b\", 1, {\n" +
                    "\t\t\t\"a\": \"b\"\n" +
                    "\t\t}]\n" +
                    "\t}\n" +
                    "}";
            System.out.println(j);
            JsonStructure jj = JsonReader.ReadString(j);
            System.out.println(jj.getValueType());
            JsonValue jjj = JsonNavigator.Navigate("a.b", jj);
            System.out.println(jjj);
        } catch (Exception e) {
            System.out.println("debug part of code error: " + e);
        }
    }
}
