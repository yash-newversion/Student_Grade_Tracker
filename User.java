package StockTradingPlatform;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class User{
    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;
    private ArrayList<Transaction> transactions;

    public User(String name, double balance){
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
        transactions = new ArrayList<>();

        loadPortfolioFromFile();
        loadTransactionsFromFile();
    }

    public String getname(){
        return name;
    }

    public double getBalance(){                 
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private void savePortfolioToFile(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("portfolio_" + name + ".txt"))) {
            for (String symbol : portfolio.keySet()) {
                bw.write(symbol + "," + portfolio.get(symbol));
                bw.newLine();
            } 
        }catch (IOException e) {
            System.out.println("Error saving portfolio for " + name);
        }      
    }

    private void loadPortfolioFromFile() {
        File file = new File("portfolio_" + name + ".txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                portfolio.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading portfolio for " + name);
        }
    }


    private void saveTransactionToFile(Transaction t) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("transactions_" + name + ".txt", true))) {

            bw.write(
                t.getType() + "," +
                t.getStock() + "," +
                t.getQuantity() + "," +
                t.getPrice()
            );
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving transaction for " + name);
        }
    }

    private void loadTransactionsFromFile() {
        File file = new File("transactions_" + name + ".txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                transactions.add(
                    new Transaction(
                        name,
                        data[1],
                        Integer.parseInt(data[2]),
                        data[0],
                        Double.parseDouble(data[3])
                    )
                );
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions for " + name);
        }
    }


    public void buyStockInternal(Stock stock, int quantity){
        double totalPrice = stock.getPrice() * quantity;

        balance -= totalPrice;
        portfolio.put(stock.getSymbol(), portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
        Transaction t = new Transaction(name, stock.getSymbol(), quantity, "Buy", stock.getPrice());
        transactions.add(t);
        savePortfolioToFile();
        saveTransactionToFile(t);
    }

    public void sellStockInternal(Stock stock, int quantity){
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        int remaining = owned - quantity;       

        if (remaining == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remaining);
        }

        balance += stock.getPrice() * quantity;
        Transaction t = new Transaction(name, stock.getSymbol(), quantity, "Sell", stock.getPrice());
        transactions.add(t);
        savePortfolioToFile();
        saveTransactionToFile(t);
    }

    public int getOwnerQuantity(String symbol){
        return portfolio.getOrDefault(symbol, 0);
    }


    public void viewPortfolio(){
        System.out.println("Portfolio of " + name + ":");
        for(String symbol : portfolio.keySet()){
            System.out.println(symbol + ": " + portfolio.get(symbol) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }

    public void showTransactionHistory(){
        if(transactions.size() == 0){
            System.out.println("No transaction found for: " + name);
            return;
        }
        System.out.println("\n📜 Transaction History of " + name);
        for(Transaction t : transactions){
            t.display();
        }
    }
}
