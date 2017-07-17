package com.lucaszanella.RequestExchange;

import java.util.Map;

/**
 * Created by lucas on 16/07/17.
 */
public class ExchangeInfo {
    public float high = 0; //Highest traded value
    public float low = 0; //Lowest traded value
    public float Last = 0; //Last traded value
    public float CoinVolume = 0; //Coin volume
    public float CurrencyVolume = 0; //Volume of local currency
    public float Buy = 0; //Buy price
    public float Sell = 0; //Sell price

    @Override
    public String toString() {
        return "debug; high = " + high;
    }
}
