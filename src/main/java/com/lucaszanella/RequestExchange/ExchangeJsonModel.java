package com.lucaszanella.RequestExchange;

import java.util.Map;

/**
 * Created by lucas on 16/07/17.
 */
public class ExchangeJsonModel {
    public class Meta {
        public String name;
        public String country;
        public String url;
        public Map<String, Map<String, String>> api;
    }
    public Meta meta;
    public String high; //Highest traded value
    public String low; //Lowest traded value
    public String last; //Last traded value
    public String volume_coin; //Coin volume
    public String volume_currency; //Volume of local currency
    public String buy; //Buy price
    public String sell; //Sell price
}