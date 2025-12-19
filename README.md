☕ Java Programming Portfolio
This repository contains a collection of Java-based applications ranging from academic management tools to AI-driven communication systems and enterprise reservation logic

Detailed Project Breakdowns

**1. Student Grade Tracker**
A robust CLI application designed to manage student academic records and generate performance reports based on entry order (Roll Number).

Core Logic: Uses nested ArrayList structures to store subject-specific marks (Java, DAA, TAFL, UHV, OS, DBMS).

Analytics: Automatically calculates class averages and identifies the highest and lowest scorers.

Status Tracking: Includes a grading engine that determines pass/fail status based on a 33% threshold.

**2. Stock Trading Platform**
A robust CLI-based financial simulation designed to manage stock market data, execute trades, and track user portfolio performance with persistent storage.

Core Logic
The platform utilizes Object-Oriented Programming (OOP) principles to manage the ecosystem through dedicated classes for Market, User, Stock, and Transaction. It employs a HashMap structure to manage users and portfolios for efficient data retrieval, while ArrayList structures track market stocks and user transaction histories.

Trading Operations
Market Management: Allows the enlisting of new stocks with specific symbols, company names, prices, and quantities.

Buy/Sell Engine: Features a business logic layer that validates transactions by checking user balance, market stock availability, and user ownership quantities.

Automated Updates: Automatically reduces market supply upon purchase and increases it upon sale, while simultaneously updating user balances.

Performance Tracking
Real-time Portfolio: Displays current stock holdings per user, including the number of shares owned and the remaining cash balance.

Transaction History: Generates a chronological log for each user, detailing the date, type of trade (Buy/Sell), quantity, and price per share.

Data Persistence
File I/O Integration: Includes a file handling system that saves and loads data from .txt files to ensure information persists across different sessions.

Individual Records: Creates specific text files for each user's portfolio and transaction history (e.g., portfolio_username.txt) to maintain organized data structures.
