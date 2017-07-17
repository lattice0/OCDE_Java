import com.google.gson.JsonElement;
import com.lucaszanella.RequestExchange.ExchangeInfo;
import com.lucaszanella.RequestExchange.ExchangeRequester;
import org.glassfish.json.JsonUtil.*;

import javax.json.JsonObject;

public class Main {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        ExchangeRequester r = new ExchangeRequester("foxbit","BTC","BRL");
        try {
            ExchangeInfo json = r.Request();
            //JsonElement a = ExchangeRequester.JsonParser.toJsonTree(json, String.class);
            //System.out.println(a);
            //System.out.println(json);
        } catch(Exception e) {

        }
    }

}
