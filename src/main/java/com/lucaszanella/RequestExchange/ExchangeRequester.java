package com.lucaszanella.RequestExchange;


import com.lucaszanella.JsonUtilities.*;
import com.lucaszanella.JsonUtilities.JsonReader;

import javax.json.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExchangeRequester {
    private static final String VARIABLES_IN_URL_SAME_STRUCTUE = "A";
    private static final String VARIABLES_IN_URL = "B";
    private static final String VARIABLES_IN_JSON_SAME_URL = "C";
    private static final String[] structure = {"high", "low", "last", "buy", "sell"};

    private String type;
    private JsonObject exchangeObject;

    public static Set<String> listExchanges(String path) throws Exception {
        JsonObject exchangesList = JsonReader.ReadFile(path).asJsonObject();
        return exchangesList.keySet();
    }

    /*
        Creates a new Exchange Requester based on the name of the exchange, the coin you want and the currency
     */
    public ExchangeRequester(String Exchange, String path) throws Exception{
        JsonObject exchangesList = JsonReader.ReadFile(path).asJsonObject();
        this.exchangeObject = exchangesList.getJsonObject(Exchange);
        this.type = this.exchangeObject.getString("type");

    }

    public String getPath(String Coin1, String Coin2) {
        String path = "";
        if (this.type.equals(VARIABLES_IN_URL_SAME_STRUCTUE)) {
            path = this.exchangeObject.getJsonObject("api").getString(Coin1+"/"+Coin2);
            System.out.println("path is "+path);
        } else if (this.type.equals(VARIABLES_IN_URL)) {
            path = this.exchangeObject.getJsonObject("api").getJsonArray(Coin1+"/"+Coin2).getString(0);
            System.out.println("path is "+path);
        } else if (this.type.equals(VARIABLES_IN_JSON_SAME_URL)) {
            path = this.exchangeObject.getString("endpoint");
            System.out.println("path is "+path);
        }
        return path;
    }

    public Set<String> getPairs() {
        return this.exchangeObject.getJsonObject("api").keySet();
    }

    public ExchangeInfo interpretResponse(String jsonResponse, String Coin1, String Coin2) throws Exception{
        JsonStructure structureResponse = JsonReader.ReadString(jsonResponse);

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
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), structureResponse);
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
            JsonArray a = this.exchangeObject.getJsonObject("api").getJsonArray(Coin1+"/"+Coin2);
            for (JsonValue entry : a.getJsonArray(1)) {
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), structureResponse);
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
                JsonValue price = JsonNavigator.Navigate(((JsonString) entry).getString(), structureResponse);
                float value = JsonNavigator.JsonValueToFloat(price);
                if (i<structure.length) {
                    exchangeInfo.put(structure[i], value);
                    i++;
                } else {
                    break;
                }
            }
        }
        ExchangeInfo r = new ExchangeInfo();
        r.Coin1 = Coin1;
        r.Coin2 = Coin2;
        r.price = exchangeInfo;
        return r;
    }
}