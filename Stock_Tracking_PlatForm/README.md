A comprehensive, console-based stock market simulation designed to demonstrate the application of Object-Oriented Programming (OOP) and File I/O for data persistence. This platform allows users to manage a virtual portfolio, track real-time market data, and execute trades through a validated transaction engine.

🚀 Key Features
Dynamic Market Management: Enlist new company stocks with unique symbols, specific pricing, and available share quantities.

User Registration & Authentication: Create unique user profiles with custom starting balances.

Robust Trading Engine: Execute "Buy" and "Sell" operations with automatic verification of market liquidity, user balance, and share ownership.

Real-time Portfolio Tracking: View current stock holdings and updated cash balances instantly after every trade.

Audit Logging: Generate detailed, timestamped transaction histories for all user activities.

Data Persistence: Automatically save and load market state and user portfolios using individual text files for each user.

🏗️ System Architecture
The project is built using a modular class structure to ensure clean logic separation:

Market.java: The central hub that manages the stock registry, user base, and transaction validation logic.

User.java: Manages personal account data, portfolio updates, and user-specific file I/O operations.

Stock.java: Represents individual market assets, tracking prices and total available shares.

Transaction.java: A record-keeping class that captures trade details and the exact time of execution.

Main.java: The user interface layer providing a menu-driven CLI for interacting with the platform.
