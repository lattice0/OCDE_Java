package com.lucaszanella.RequestExchange;


import okhttp3.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.StringReader;


public class ExchangeRequester {
    private static String protocol = "https"; //Always https, http not allowed >:)
    private String ExchangeApiDomain;
    private String ExchangeName;
    private String ExchangeCoin;
    private String ExchangeApiPath;
    private String ExchangeCurrency;
    //private ExchangeJsonModel Meta;
    private java.io.Reader Reader;

    private static OkHttpClient httpsClient = new OkHttpClient.Builder()
                                    //.addNetworkInterceptor(new UserAgentInterceptor(userAgent))
                                    .build();
    private Request okhttpApiRequester;

    /*
        Creates a new Exchange Requester based on the name of the exchange, the coin you want and the currency
     */
    public ExchangeRequester(String Exchange, String Coin, String Currency) {
        try {
            Reader = new FileReader("exchanges.json");
            javax.json.JsonReader jsonReader = Json.createReader(Reader);
            JsonObject exchangesList = jsonReader.readObject();
            JsonObject exchangeObject = exchangesList.getJsonObject("foxbit");
            JsonObject exchangeMetadata = exchangeObject.getJsonObject("meta");
            this.ExchangeName = exchangeMetadata.getString("name");
            this.ExchangeCoin = Coin;
            this.ExchangeCurrency = Currency;
            this.ExchangeApiPath = exchangeMetadata.
                    getJsonObject("api").
                    getJsonObject(Coin).
                    getString(Currency);//POSSIBLE EXCEPTION HERE, TAKE CARE LATER
            this.okhttpApiRequester = new Request.Builder().
                    url(protocol + "://" + this.ExchangeApiPath).
                    build();
        } catch (Exception e) {
            System.out.println("error... " + e); //Detail errors here
        }
    }

    /*
        Does the actual price request, parses it and returns in the format "ExchangeInfo"
     */
    public ExchangeInfo Request() throws Exception {
        Response r  = httpsClient.newCall(okhttpApiRequester).execute();
        String json = r.body().string();
        System.out.println(json);

        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject jobj = jsonReader.readObject();
        System.out.println(jobj.getJsonNumber("high"));
        return new ExchangeInfo();
    }
}