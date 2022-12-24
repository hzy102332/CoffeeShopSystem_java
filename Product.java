package CoffeeShop;

import javax.sound.sampled.Port;
import java.util.*;

public class Product {
    public enum Kind {
        Drink, Food;
    }
    private Kind kind;
    private String name;
    private Integer ID;
    private double price;
    private HashMap<String, List<Double>> Products;
    private int Daily_sales;
    private int total_sales;
    private int inventory;
    public Product() {
        this.Products = new HashMap<>();
    }
    //Food
    public Product(String name, Integer ID, double price, int Daily_sale, int total_sales, int inventory) {
        this.name = name;
        this.ID = ID;
        this.price = price;
        this.Daily_sales = Daily_sale;
        this.total_sales = total_sales;
        this.inventory = inventory;
        this.kind = Kind.Food;
    }
    //Drink
    public Product(String name, Integer ID, double price, int Daily_sale, int total_sales) {
        this.name = name;
        this.ID = ID;
        this.price = price;
        this.kind = Kind.Drink;
        this.Daily_sales = Daily_sale;
        this.total_sales = total_sales;
    }

    public void addProducts(Product product) {
        /**list
         * 0: ID
         * 1: price
         * 2: Daily_sales
         * 3: total_sales
         * 4: KIND (0: Food, 1: Drink)
         * 5. Inventory (Food)
         * */
        List<Double> list = new ArrayList<>();
        if (product.getKind() == Kind.Drink) {
            list.add((double) product.getID());
            list.add(product.getPrice());
            list.add((double) product.getDaily_sales());
            list.add((double) product.getTotal_sales());
            list.add(1.0);
        } else {
            list.add((double) product.getID());
            list.add(product.getPrice());
            list.add((double) product.getDaily_sales());
            list.add((double) product.getTotal_sales());
            list.add(0.0);
            list.add((double) product.getInventory());
        }
        Products.put(product.getName(), list);
    }

    public void addDailySale(Product product, String num) {
        product.setDaily_sales(product.getDaily_sales() + Integer.parseInt(num));
        product.setTotal_sales(product.getTotal_sales() + Integer.parseInt(num));
    }
    public int getInventory() {
        return inventory;
    }

    public int getDaily_sales() {
        return Daily_sales;
    }

    public int getTotal_sales() {
        return total_sales;
    }

    public HashMap<String, List<Double>> getAllProducts() {
        return Products;
    }

    public List<Double> getProductsInfo(String pname){
        return Products.get(pname);
    }
    public List<String> getDailySales() {
        List<String> s = new ArrayList<>();
        for (String key : Products.keySet()) {
            int daily = (int) Math.round(Products.get(key).get(2));
            s.add(key + ": " + daily);
        }
        return s;
    }

    public List<String> getAnnualSales() {
        List<String> s = new ArrayList<>();
        for (String key : Products.keySet()) {
            int annual = (int) Math.round(Products.get(key).get(3));
            s.add(key + ": " + annual);
        }
        return s;
    }

    public List<String> getRestNumber() {
        List<String> s = new ArrayList<>();
        for (String key : Products.keySet()) {
            if (Products.get(key).size() == 6) {
                int annual = (int) Math.round(Products.get(key).get(3));
                int total = (int) Math.round(Products.get(key).get(5));
                s.add(key + ": " + (total - annual));
            }
        }
        return s;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public double getPrice() {
        return price;
    }

    public Kind getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Product: " + name + " ID: " + ID + ", Price: " + price;
    }

    public void setDaily_sales(int daily_sales) {
        Daily_sales = daily_sales;
    }

    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }
}
