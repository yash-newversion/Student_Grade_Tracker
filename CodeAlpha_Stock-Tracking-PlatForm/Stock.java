package StockTradingPlatform;

class Stock {
    private String Symbol;
    private String name;
    private double price;
    private int quantity;

    public Stock(String Symbol, String name, double price, int quantity){
        this.Symbol = Symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // all are getter functions;
    public String getSymbol(){
        return Symbol;
    }

    public double getPrice(){
        return price;
    }

    public String getname(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void increasedQuantity(int amount){
        quantity += amount;
    }

    public void reduceQuantity(int amount){
        if(amount <= quantity){
            quantity -= amount;
        }    
    }

    public void display(){
        System.out.println("Symbol: " + Symbol + " = Company Name: " + name + "Price/Share : $ " + price + " | Available Share in Market: " + quantity);
    }
}
