package StockTradingPlatform;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Market market = new Market();

        while(true){
            showoption();
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 9){
                System.out.println("Existing......");
                break;
            }

            switch(choice){

                case 1:{
                    System.out.print("Company Symbol: ");
                    String Symbol = sc.nextLine();

                    System.out.print("Company Name: ");
                    String Companyname = sc.nextLine();

                    System.out.print("Stock Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    market.addStock(new Stock(Symbol, Companyname, price, quantity));
                    break;
                }
                case 2: {
                System.out.print("User Name: ");
                String username = sc.nextLine();

                System.out.print("Deposit Balance: ");
                double balance = sc.nextDouble();
                sc.nextLine(); // Consume newline left by nextDouble()

                market.addUser(new User(username, balance));
                System.out.println("User inserted successfully");
                
                System.out.print("Would you like to buy or sell the stock? (Yes/No): ");
                String ans = sc.nextLine();

                if (ans.equalsIgnoreCase("Yes")) {
                    System.out.println("For buy the stock: 1");
                    System.out.println("For sell the stock: 2");
                    System.out.println("Available Companies: 3");
                    System.out.print("Enter choice: ");

                    int choice1 = sc.nextInt();
                    sc.nextLine(); // Consume newline left by nextInt()

                    switch (choice1) {
                        case 1: {
                            System.out.print("Stock Symbol: ");
                            String symbol = sc.nextLine();

                            System.out.print("Quantity: ");
                            int qty = sc.nextInt();
                            sc.nextLine(); // Consume newline

                            // Changed 'name' to 'username' to match the variable declared above
                            market.buyStock(username, symbol, qty); 
                            break;
                        }
                        case 2: {
                            System.out.print("Stock Symbol: ");
                            String symbol = sc.nextLine();

                            System.out.print("Quantity: ");
                            int qty = sc.nextInt();
                            sc.nextLine();

                            market.sellStock(username, symbol, qty); 
                            break;
                        }
                        case 3:{
                            market.showMarket();
                            break;
                        }
                        default:
                            System.out.println("Wrong Choice: ");
                            System.out.println("Redirecting to Main menu");
                    }
                }
                break;
            }
                case 3:{
                    System.out.print("User Name: ");
                    String name = sc.nextLine();

                    System.out.print("Stock Symbol: ");
                    String symbol = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    market.buyStock(name, symbol, qty);   // 🔧 ONLY MARKET CALL
                    break;
                }
                case 4:{
                    System.out.print("User Name: ");
                    String name = sc.nextLine();

                    System.out.print("Stock Symbol: ");
                    String symbol = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    market.sellStock(name, symbol, qty);  // 🔧 ONLY MARKET CALL
                    break;
                }
                case 5:{
                    System.out.print("User Name: ");
                    String name = sc.nextLine();

                    User user = market.getUser(name);
                    if(user != null) user.viewPortfolio();
                    else System.out.println("User not found");
                    break;
                }

                case 6:{
                    System.out.println("Enter your name: ");
                    String name = sc.nextLine();

                    User user = market.getUser(name);
                    if(user == null){
                        System.out.println("User not found");
                        break;
                    }                    
                    user.showTransactionHistory();
                    break;
                }

                case 7:{
                    market.showMarket();
                    break;
                } 

                case 8:{
                    market.allusers();
                    break;
                }

                default:
                    System.out.println("Make Valid Choice!");
                
            }

        }
    }

    public static void showoption(){
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Enlist stocks");
        System.out.println("2. Register YourSelf");
        System.out.println("3. Buy stock (Execute a transaction)");
        System.out.println("4. Sell stock (Execute a transaction)");
        System.out.println("5. View User Portfolio (Current holdings)");
        System.out.println("6. View User Transaction History");
        System.out.println("7. Display company market data");
        System.out.println("8. Display registered users");
        System.out.println("9. For Existing the Loop: ");
        System.out.println("-----------------");
    }
}


// we are going to give the choice to user that what they want to do
/*option 1: enlist the stock
            display the currect condition of that stock
option 2: enlist the user
            inside this we also going to give the flexibiltiy to user that he can directly get the option for
            buy stock
            sell stock
            user portfolio
            transaction history
            there is not use to see the portfolio or trasaction becuase user is new at this point 
            exit from this. not from the main loop
option 3: buystock
option 4: sell stock
option 5: user portfolio
option 6 if user want user portfolio history
case 7: to see the transaction history
option : display stock (for company use)
 */
