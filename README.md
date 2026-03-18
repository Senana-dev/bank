# 🏦 Banking REST API (Spring Boot)

A simple banking backend application built with Spring Boot.  
This project provides RESTful APIs for managing bank accounts, including account creation, deposit, withdrawal, and account lookup.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

---

## 📌 Features

- Create a new bank account
- Get all accounts
- Get account by account number
- Deposit money
- Withdraw money

---

## 🏗️ Project Structure
src/main/java/com/sena/bank  

  ㄴ controller    
  ㄴ service  
  ㄴ repository  
  ㄴ entity  
  ㄴ BankApplication.java

---

## ⚙️ How to Run
### 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/banking-api.git  
cd banking-api

### 2. Run the application
./mvnw spring-boot:run

Windows:
mvnw.cmd spring-boot:run

### 3. Access the API
http://localhost:8081

---

## 📬 API Endpoints

### ➤ Create Account

POST `/api/accounts`
{
"accountNumber": "1001",
"ownerName": "User Name",
"balance": 1000
}

---

### ➤ Get All Accounts

GET `/api/accounts`

---

### ➤ Get Account

GET '/api/accounts/{accountNumber}'

---

### ➤ Deposit

POST '/api/accounts/{accountNumber}/deposit'
{
"amount": 500
}

---

### ➤ Withdraw

POST '/api/accounts/{accountNumber}/withdraw'
{
"amount": 200
}

---

## 🧪 Test Database

- H2 in-memory database
- Runs automatically when the app starts
- No external setup required

---

## 💡 Future Improvements

- Transfer API (account-to-account transactions)
- Transaction history
- Global exception handling
- DTO pattern for request/response
- Swagger/OpenAPI documentation
- MySQL/PostgreSQL integration

---

## 👤 Author

Sena Kim

---

## 📄 License

This project is for educational purposes.
