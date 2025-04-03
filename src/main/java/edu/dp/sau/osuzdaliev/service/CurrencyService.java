package edu.dp.sau.osuzdaliev.service;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import java.io.IOException;
@Service
public class CurrencyService {
    private static final String PRIVATBANK_API_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public double getUsdRate() {
        return getExchangeRate("USD");
    }
    public double getEurRate() {
        return getExchangeRate("EUR");
    }
    private double getExchangeRate(String currencyCode) {
        try {
            String json = Jsoup.connect(PRIVATBANK_API_URL).ignoreContentType(true).execute().body();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("ccy").equalsIgnoreCase(currencyCode)) {
                    return obj.getDouble("sale");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
