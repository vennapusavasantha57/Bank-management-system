# Bank Management System

Simple Java console-based Bank Management System using JDBC and MySQL.

## Features
- Create customer & account (Savings/Current/FD)
- Deposit, Withdraw, Transfer
- Transaction logging
- Basic DAO + Service layered structure

## Tech stack
- Java 11
- Maven
- MySQL
- JDBC

## Setup
1. Install MySQL and create a user. Run `schema.sql` to create database and tables.
2. Update DB credentials in `src/main/java/com/vasantha/bank/util/DBConnection.java`.
3. Build:
