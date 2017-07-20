import com.lucaszanella.JsonNavigator.JsonNavigator;
import com.lucaszanella.RequestExchange.ExchangeInfo;
import com.lucaszanella.RequestExchange.ExchangeRequester;

import javax.json.*;
import java.io.StringReader;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        ExchangeRequester r = new ExchangeRequester("mercado_bitcoin","BTC","BRL");
        try {
            //Map<String, Number> json = r.Request();
            //System.out.println("json is :" + json);
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
                "\t},\n" +
                "\t\"j\": {\n" +
                "\t\t\"k\": [\"a\", \"b\", 1, {\n" +
                "\t\t\t\"a\": \"b\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}";
        //j = "[2253.9,6.47826599,2254,43.96127634,-102.1,-0.0433,2253.9,38451.02888854,2400,2230.9]";
        System.out.println(j);
        JsonReader jsonReader = Json.createReader(new StringReader(j));
        JsonStructure jj = jsonReader.read();
        System.out.println(jj.getValueType());
        JsonValue jjj = JsonNavigator.Navigate("a.b", jj);
        System.out.println(jjj);

    }

}
