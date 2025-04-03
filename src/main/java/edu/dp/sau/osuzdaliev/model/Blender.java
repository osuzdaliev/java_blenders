package edu.dp.sau.osuzdaliev.model;
public class Blender {
    private String name;
    private double price;
    private double priceUsd;
    private double priceEur;
    private String link;
    public Blender(String name, double price, double priceUsd, double priceEur, String link) {
        this.name = name;
        this.price = price;
        this.priceUsd = priceUsd;
        this.priceEur = priceEur;
        this.link = link;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getPriceUsd() { return priceUsd; }
    public double getPriceEur() { return priceEur; }
    public String getLink() { return link; }
}


