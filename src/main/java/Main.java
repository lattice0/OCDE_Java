import com.lucaszanella.JsonNavigator.JsonNavigator;
import com.lucaszanella.RequestExchange.ExchangeInfo;
import com.lucaszanella.RequestExchange.ExchangeRequester;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.StringReader;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        ExchangeRequester r = new ExchangeRequester("foxbit","BTC","BRL");
        try {
            //Map<String, String> json = r.Request();
            //JsonElement a = ExchangeRequester.JsonParser.toJsonTree(json, String.class);
            //System.out.println(a);
            //System.out.println(json);
        } catch(Exception e) {

        }
        String j = "{\n" +
                "\t\"a\": {\n" +
                "\t\t\"b\": {\n" +
                "\t\t\t\"c\": \"d\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"e\": \"f\",\n" +
                "\t\"g\": {\n" +
                "\t\t\"h\": \"i\"\n" +
                "\t}\n" +
                "}";
        JsonReader jsonReader = Json.createReader(new StringReader(j));
        JsonObject jj = jsonReader.readObject();
        JsonValue jjj = JsonNavigator.Navigate("g.h", jj);
        System.out.println(jjj);
    }

}
