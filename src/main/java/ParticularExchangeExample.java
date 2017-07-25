import com.lucaszanella.RequestExchange.ExchangeInfo;
import com.lucaszanella.RequestExchange.ExchangeRequester;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class ParticularExchangeExample {
    public static String Exchange = "kraken";
    public static String Coin1 = "bitcoin";
    public static String Coin2 = "dollar";

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("CryptoPOS");
        try {
            ExchangeRequester r = new ExchangeRequester(Exchange, "ODCE/exchanges.json");
            System.out.println(r.getPairs());
            Request api = new Request.Builder().
                    url("https://"+r.getPath(Coin1, Coin2)).
                    build();
            System.out.println(api);
            System.out.println("requesting...");
            String response = new OkHttpClient.Builder().
                                build().
                                newCall(api).
                                execute().body().string();
            System.out.println(response);
            ExchangeInfo p = r.interpretResponse(response, Coin1, Coin2);
            System.out.println("price is: " + p.price);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

}
