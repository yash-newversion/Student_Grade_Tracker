package StockTradingPlatform;

import java.time.LocalDateTime;

class Transaction {
    private String user;
    private String stock;
    private int quantity;
    private String type;
    private double price;
    private LocalDateTime date;

    public Transaction(String user, String stock, int quantity, String type, double price){
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
        this.date = LocalDateTime.now();
    }

    // file I/O Getters
    public String getUser() {
        return user;
    }

    public String getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }
    
    public void display(){
        System.out.println(date + " - " + user + " " + type + " " + quantity + " shares of " + stock + " " + " at $ " + price);
    }
}
