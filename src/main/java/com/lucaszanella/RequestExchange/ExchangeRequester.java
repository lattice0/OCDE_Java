package com.lucaszanella.RequestExchange;


import com.lucaszanella.JsonUtilities.*;
import com.lucaszanella.JsonUtilities.JsonReader;
import okhttp3.*;

import javax.json.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class ExchangeRequester {
    private static String protocol = "https"; //Always https, http not allowed >:)
    private static final String VARIABLES_IN_URL_SAME_STRUCTUE = "A";
    private static final String VARIABLES_IN_URL = "B";
    private static final String VARIABLES_IN_JSON_SAME_URL = "C";
    private static final String[] structure = {"high", "low", "last", "buy", "sell"};

    private String type;
    //private ExchangeJsonModel Meta;
    private java.io.Reader Reader;
    private JsonObject exchangeObject;
    private OkHttpClient httpsClient;

    /*
        Creates a new Exchange Requester based on the name of the exchange, the coin you want and the currency
     */
    public ExchangeRequester(String Exchange) {
        try {
            JsonObject exchangesList = JsonReader.ReadFile("exchanges_v2.json").asJsonObject();
            this.exchangeObject = exchangesList.getJsonObject(Exchange);
            this.type = this.exchangeObject.getString("type");
        } catch (Exception e) {
            System.out.println("error... " + e); //Detail errors here
        }
    }
    /*
        Replaces the string "{n}" from url by the n-th string of stringsToInsert
     */
    /*
    public static String ReplaceApiUrl(String url, String[] stringsToInsert) {
        int i = 0;
        for (String s: stringsToInsert) {
            System.out.println("replacing " + s);
            url = url.replaceFirst(Pattern.quote("{"+String.valueOf(i)+"}"), s);
            i++;
            System.out.println(url);
        }
        return url;
    }
    */

    /*
        Does the actual price request, parses it and returns in the format "ExchangeInfo"
     */
    public Map<String, Number> Request(String Coin1, String Coin2) throws Exception {
        this.httpsClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new UserAgentInterceptor(userAgent))
                .build();
        String path = "";
        if (this.type.equals(VARIABLES_IN_URL_SAME_STRUCTUE)) {
            path = this.exchangeObject.getJsonObject("api").getString(Coin1+"/"+Coin2);
            System.out.println("path is "+path);
        } else if (this.type.equals(VARIABLES_IN_URL)) {
            path = this.exchangeObject.getJsonObject("api").getString(Coin1+"/"+Coin2);
            System.out.println("path is "+path);
        } else if (this.type.equals(VARIABLES_IN_JSON_SAME_URL)) {
            path = this.exchangeObject.getString("endpoint");
            System.out.println("path is "+path);
        }
        Request okhttpApiRequester = new Request.Builder().
                url(protocol + "://" + path).
                build();

        Response r = httpsClient.newCall(okhttpApiRequester).execute();
        String json = r.body().string();
        System.out.println(json);

        JsonStructure jsonResponse = JsonReader.ReadString(json);

        Map<String, Number> exchangeInfo = new HashMap<>();
        /*
            Let's iterate through the exchangeObject to see where to find
            each node in the api response from each exchange. For example,
            MercadoBitcoin's 'high' price is found at its json response by
            navigating in ticker and then high, so MercadoBitcoin's entry
            in exchanges.json says that 'high' is located at 'ticker.high'
         */
        if (this.type.equals(VARIABLES_IN_URL_SAME_STRUCTUE)) {//A
            System.out.println("type A");
            int i = 0;
            for (JsonValue entry : this.exchangeObject.getJsonArray("structure")) {
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), jsonResponse);
                float value = JsonNavigator.JsonValueToFloat(price);
                if (i<structure.length) {
                    exchangeInfo.put(structure[i], value);
                    i++;
                } else {
                    break;
                }
            }
        } else if (this.type.equals(VARIABLES_IN_URL))  {//B
            System.out.println("type B");
            int i = 0;
            for (JsonValue entry : this.exchangeObject.getJsonObject("api").getJsonArray(Coin1+"/"+Coin2)) {
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), jsonResponse);
                float value = JsonNavigator.JsonValueToFloat(price);
                if (i<structure.length) {
                    exchangeInfo.put(structure[i], value);
                    i++;
                } else {
                    break;
                }
            }
        } else if (this.type.equals(VARIABLES_IN_JSON_SAME_URL))  {//C
            System.out.println("type C");
            int i = 0;
            for (JsonValue entry : this.exchangeObject.getJsonObject("api").getJsonArray(Coin1+"/"+Coin2)) {
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), jsonResponse);
                float value = JsonNavigator.JsonValueToFloat(price);
                if (i<structure.length) {
                    exchangeInfo.put(structure[i], value);
                    i++;
                } else {
                    break;
                }
            }
        }
        return exchangeInfo;
    }
}