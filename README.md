# Selenium Automation Framework with Ritchie Bros Auction website
## 📌 Overview
This project is a Selenium-based automation framework built using Java, TestNG, and Maven.

## 🧱 Tech Stack
- Java 17
- Selenium 4
- TestNG
- Maven
- WebDriverManager
- GitHub Actions (CI)

## 🏗️ Framework Design

- Page Object Model (POM)
- Separate Page & PageElements classes
- ThreadLocal WebDriver
- ConfigReader for environment configs
- Retry Analyzer for flaky tests retries

## ⚙️ Setup Instructions

### Prerequisites
- Java 17+
- Maven installed
- Chrome browser installed

### Run Locally

```bash
mvn clean test
```

## ⚙️ Write your first test and view reports
- Create a test class in `src/test/java/com/ritchiebros/tests`
- Use TestNG annotations to define test methods
- use the page class for writing methods to interact with the web elements
- Run the test class using TestNG
- View reports in `target/surefire-reports`

## To-dos
- local development caching setup in DriverFactory 
- environment for faster rendering, whitelisted ips
- parallel execution setup in TestNG XML
