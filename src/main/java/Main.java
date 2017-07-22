import com.lucaszanella.JsonUtilities.*;
import com.lucaszanella.RequestExchange.ExchangeInfo;
import com.lucaszanella.RequestExchange.ExchangeRequester;


import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import java.io.StringReader;
import java.util.Map;

public class Main {
    public static String Exchange = "kraken";
    public static String Crypto = "bitcoin";
    public static String Fiat = "dollar";

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("CryptoPOS");
        try {
            /*
            System.out.println(ExchangeRequester.ReplaceApiUrl("api.bitfinex.com/v2/ticker/t{0}{1}",
                    new String[]{"BTC", "USD"}));
            //Exchanges
            JsonStructure exchanges = JsonReader.ReadFile("exchanges.json");
            String selectedExchangeName = ((JsonObject) exchanges).
                    getJsonObject(Exchange).
                    getJsonObject("meta").
                    getJsonString("name").
                    toString();
            System.out.println("Selected exchange: " + selectedExchangeName);

            //Coins (Crypto & Fiat)
            JsonStructure coins = JsonReader.ReadFile("coins.json");
            String selectedCryptoName = ((JsonObject) coins).
                    getJsonObject("crypto").
                    getJsonObject(Crypto).
                    getJsonString("name").
                    toString();
            System.out.println("Selected crypto: " + selectedCryptoName);

            String selectedFiatName = ((JsonObject) coins).
                    getJsonObject("fiat").
                    getJsonObject(Fiat).
                    getJsonString("name").
                    toString();
            System.out.println("Selected fiat: " + selectedFiatName);

            */
            ExchangeRequester r = new ExchangeRequester(Exchange);
            System.out.println("requesting...");
            Map<String, Number> json = r.Request(Crypto, Fiat);
            System.out.println("json is :" + json);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }


        Boolean Debug = false;
        if (Debug) {
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
                //j = "[2253.9,6.47826599,2254,43.96127634,-102.1,-0.0433,2253.9,38451.02888854,2400,2230.9]";
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

}
