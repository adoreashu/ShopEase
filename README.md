# ShopEase - E-Commerce QA Automation Project

## Project Overview
ShopEase is a realistic, fully functional e-commerce web application designed as a portfolio project for Software Engineering and QA Automation. It includes a frontend built with HTML/CSS/JS, a backend powered by Spring Boot (Java), an H2 database (compatible with MySQL queries), and a complete End-to-End QA Automation framework.

## Architecture
- **Frontend**: HTML5, Bootstrap 5, Vanilla JS
- **Backend**: Java 17, Spring Boot 3, Spring Data JPA, Spring Security (JWT)
- **Database**: H2 Database (configured with MySQL compatibility mode for easy local execution without docker)
- **QA Automation**: Selenium WebDriver, TestNG, REST Assured, JDBC, Allure Reports
- **CI/CD**: GitHub Actions

## Features
- **Authentication**: JWT-based registration and login.
- **Product Catalog**: Browse and search products.
- **Shopping Cart**: Add, update, and remove items.
- **Checkout**: Mock checkout process generating an order.

## How to Run the Application

1. **Backend**:
   ```bash
   cd backend
   ./mvnw clean spring-boot:run
   ```
   *The backend runs on http://localhost:8080. It automatically seeds the database with initial products and users.*

2. **Frontend**:
   ```bash
   cd frontend
   python -m http.server 3000
   ```
   *Open your browser and navigate to http://localhost:3000*

## Test Credentials
- **Email**: `shahil.pratap@example.com`
- **Password**: `Password@123##`

## How to Run Automation Tests

Make sure both the backend and frontend are running.
```bash
cd automation
../backend/mvnw clean test
```

### View Allure Report
```bash
cd automation
../backend/mvnw allure:report
```
You can then open `automation/target/site/allure-maven-plugin/index.html`.

## Automation Scope
- **UI Tests**: Written in Selenium for end-to-end user flows (Login, Checkout, etc.)
- **API Tests**: Written in REST Assured to validate status codes and schemas.
- **Database Tests**: Written with JDBC to query the state of H2 after transactions.

## CI/CD Pipeline
The `.github/workflows/test.yml` file is configured to spin up the backend, spin up the frontend web server, wait for both to be healthy, execute the full Maven TestNG suite, and upload the Allure results as an artifact.
