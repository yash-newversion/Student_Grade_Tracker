package StockTradingPlatform;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Market {
    private ArrayList<Stock> stocks;
    private HashMap<String, User> users; 

    private static final String USER_FILE = "users.txt";
    private static final String STOCK_FILE = "stocks.txt";

    public Market(){
        stocks = new ArrayList<>();
        users = new HashMap<>();

        loadUsersFromFile();   
        loadStocksFromFile();
    }

    // ---------------- STOCK METHODS ----------------

    public void addStock(Stock stock){
        for(Stock curr: stocks){
            if(curr.getSymbol().equals(stock.getSymbol())){
                System.out.println("Stock is already available: ");
                return;
            }
        }

        stocks.add(stock);
        saveStocksToFile();
        System.out.println("Stock added successfully: " + stock.getSymbol());
    }

    public Stock getStock(String symbol){
        for(Stock stock : stocks){
            if(stock.getSymbol().equals(symbol)){
                return stock;
            }
        }
        return null; 
    }

    public void showMarket(){
        System.out.println("Market Data");
        for(Stock stock : stocks){
            stock.display();
        }
    }

   // ---------------- USER METHODS ----------------

    public void addUser(User user){
        if(users.containsKey(user)){
            return;
        }
        users.put(user.getname(), user);
        saveUsersToFile();
    }

    public User getUser(String name){
        return users.get(name);
    }

    public void showAllUsers(){
        System.out.println("Registered Users");
        for(String name : users.keySet()){
            System.out.println(name);
        }
    }

    // ---------- BUSINESS LOGIC ----------
    public void buyStock(String username, String stocksymbol, int quantity){
        // here we are extracting the User of User datatype from users which of HashSet type;
        // user object to get the keypair;
        User user = users.get(username);

        if(user == null){
            System.out.println("User not found");
            return;
        }

        Stock stock = getStock(stocksymbol);
        if(stock == null){
            System.out.println("Stock not found");
            return;
        }

        if(stock.getQuantity() < quantity){
            System.out.println("Not enough stock in market:");
            return;
        }

        double totalPrice = stock.getPrice() * quantity;
        if(user.getBalance() < totalPrice){
            System.out.println("Insufficient balance:");
            return;
        }


        stock.reduceQuantity(quantity);
        user.buyStockInternal(stock, quantity);
        saveUsersToFile();  
        saveStocksToFile();       
        System.out.println("Transaction Successful: ");
    }

    public void sellStock(String username, String stocksymbol, int quantity){
        User user = users.get(username);
        if(user == null){
            System.out.println("User not found");
            return;
        }

        Stock stock = getStock(stocksymbol);
        if(stock == null){
            System.out.println("Stock not found");
            return;
        } 
        
        if(user.getOwnerQuantity(stocksymbol) < quantity){
            System.out.println("Not enough shares to sell");
            return;
        }

        stock.increasedQuantity(quantity);
        user.sellStockInternal(stock, quantity);

        saveUsersToFile(); 
        saveStocksToFile();
        System.out.println("Transaction successful");
    }

    // Correct way to iterate over keys
    public void allusers(){
        for(String name: users.keySet()){
            System.out.println(name);
        }
    }

    private void loadUsersFromFile() {
        File file = new File(USER_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                users.put(d[0], new User(d[0], Double.parseDouble(d[1])));
            }
        } catch (IOException e) {
            System.out.println("Error loading users");
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User u : users.values()) {
                bw.write(u.getname() + "," + u.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users");
        }
    }

    private void loadStocksFromFile() {
        File file = new File(STOCK_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                stocks.add(new Stock(
                        d[0], d[1],
                        Double.parseDouble(d[2]),
                        Integer.parseInt(d[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("Error loading stocks");
        }
    }

    private void saveStocksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STOCK_FILE))) {
            for (Stock s : stocks) {
                bw.write(s.getSymbol() + "," +
                        s.getname() + "," +
                        s.getPrice() + "," +
                        s.getQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving stocks");
        }
    }
}
