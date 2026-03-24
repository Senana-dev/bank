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

- Account management (create, read)
- Deposit and withdrawal
- Money transfer with transactional integrity
- Transaction history tracking
- Global exception handling
- Request validation using DTO

---

## 🏗️ Project Structure
  src/main/java/com/dev/bank  
├── controller  
│ ├── AccountController.java  
│ └── TransactionController.java  
├── service  
│ └── AccountService.java  
├── repository  
│ ├── AccountRepository.java  
│ └── TransactionHistoryRepository.java  
├── entity  
│ ├── Account.java  
│ ├── TransactionHistory.java  
│ └── TransactionType.java  
├── dto  
│ ├── AccountResponse.java  
│ ├── AmountRequest.java  
│ ├── CreateAccountRequest.java  
│ ├── TransactionHistoryResponse.java  
│ └── TransferRequest.java  
├── exception  
│ ├── ErrorResponse.java  
│ └── GlobalExceptionHandler.java  
└── BankApplication.java  

---

## ⚙️ How to Run
### 1. Clone the repository
git clone https://github.com/Senana-dev/bank.git  
cd bank

### 2. Run the application
./mvnw spring-boot:run

Windows:
mvnw.cmd spring-boot:run

### 3. Access the API
http://localhost:8081

---

## 📬 API Endpoints

### ➤ Create Account

POST '/api/accounts'
{
"accountNumber": "1001",
"ownerName": "User Name",
"balance": 1000
}

---

### ➤ Get All Accounts

GET '/api/accounts'

---

### ➤ Transfer Money

POST '/api/accounts/transfer'
{
  "fromAccountNumber": "XXXX",
  "toAccountNumber": "XXXX",
  "amount": 200
}

---

### ➤ Get Transfer Money

GET '/api/transactions'

---

### ➤ GET Transaction History

GET '/api/transactions'

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

- Swagger / OpenAPI documentation
- PostgreSQL integration
- Authentication & authorization
- Pagination for transaction history

---

## 👤 Author

Sena Kim

---

## 📄 License

This project is for educational purposes.
