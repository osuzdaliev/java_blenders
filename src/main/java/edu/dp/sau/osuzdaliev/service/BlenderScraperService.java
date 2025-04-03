package edu.dp.sau.osuzdaliev.service;
import edu.dp.sau.osuzdaliev.model.Blender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
@Service
public class BlenderScraperService {
    private static final String BASE_URL = "https://www.foxtrot.com.ua/uk/shop/blendery";
    private final CurrencyService currencyService;
    public BlenderScraperService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    public List<Blender> getBlenders(String brand) {
        List<Blender> blenders = new ArrayList<>();
        Set<String> seenLinks = new HashSet<>();
        String baseUrl = brand.equalsIgnoreCase("All") ? BASE_URL : BASE_URL + "_" + brand;
        try {
            int page = 1;
            String prevUrl = "";
            double usdRate = currencyService.getUsdRate();
            double eurRate = currencyService.getEurRate();
            while (true) {
                String url = page == 1 ? baseUrl + ".html" : baseUrl + ".html?page=" + page;
                Document doc = Jsoup.connect(url).get();
                if (doc.location().equals(prevUrl)) {
                    break;
                }
                prevUrl = doc.location();
                Elements products = doc.select(".listing__body .card");
                if (products.isEmpty()) {
                    break;
                }
                for (Element product : products) {
                    String name = product.select(".card__title").text();
                    String priceStr = extractFinalPrice(product.select(".card__price").text());
                    String link = "https://www.foxtrot.com.ua" + product.select("a").attr("href");
                    if (name.isEmpty() || priceStr.isEmpty()) continue;
                    if (seenLinks.contains(link)) {
                        continue;
                    }
                    seenLinks.add(link);
                    double price = Double.parseDouble(priceStr);
                    double priceUsd = price / usdRate;
                    double priceEur = price / eurRate;
                    blenders.add(new Blender(name, price, priceUsd, priceEur, link));
                }
                page++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blenders;
    }
    private String extractFinalPrice(String priceText) {
        if (priceText == null || priceText.isEmpty()) {
            return "";
        }
        if (priceText.contains("грн")) {
            priceText = priceText.substring(priceText.indexOf("грн") + 3);
        }
        return priceText.replaceAll("[^0-9]", "").trim();
    }
}










