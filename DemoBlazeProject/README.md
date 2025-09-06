# Demoblaze Capstone Project 🚀

Automation framework built on the [Demoblaze](https://www.demoblaze.com/) demo e-commerce site.  
Covers **signup, login, product browsing, cart operations, and checkout** using Selenium + TestNG.  

This project was created as a **Capstone Project for Wipro NGA Training**.  

---

## 📌 Overview
This project demonstrates:
- Professional **Page Object Model (POM)** design
- Robust **TestNG framework** integration
- **Cross-browser testing** with Chrome & Firefox
- **ExtentReports & Excel Reports** for result tracking
- **CI/CD integration with Jenkins**
- **Test Management with Jira**
- **Scalable test automation framework** suitable for real-world e-commerce apps

---

## 🛠️ Tech Stack
- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** ExtentReports + Excel (Apache POI)  

---

## 🧰 Tools
- **Jenkins** → Continuous Integration & Continuous Delivery (CI/CD)  
- **Jira** → Test case management, defect tracking, and agile workflow  
- **GitHub** → Version control & collaboration  

---

## 📂 Project Structure

```
Capstone_DemoBlaze/
├─ pom.xml
├─ README.md
├─ testng.xml
├─ src
│  ├─ main/java/com/demoblaze/pages
│  │  ├─ BasePage.java
│  │  ├─ HomePage.java
│  │  ├─ LoginPage.java
│  │  ├─ ProductPage.java
│  │  ├─ CartPage.java
│  │  └─ components/
│  │     └─ ModalsPage.java
│  │
│  ├─ main/java/com/demoblaze/utils
│  │  └─ ExcelUtil.java
│  │
│  └─ test/java/com/demoblaze/tests
│     ├─ base/
│     │  ├─ BaseTest.java
│     │  └─ TestResultListener.java
│     │
│     ├─ auth/
│     │  ├─ SignupLoginTest.java              # Create new user & login
│     │  ├─ SignupLoginLogoutTest.java        # Login & logout flow
│     │  ├─ DuplicateSignupTest.java          # Validate duplicate user signup
│     │  ├─ EmptyLoginValidationTest.java     # Login with empty fields
│     │  └─ WrongPasswordTest.java            # Login with wrong password
│     │
│     ├─ product/
│     │  └─ ProductDetailsTest.java           # Validate product details
│     │
│     ├─ cart/
│     │  ├─ AddToCartTest.java                # Add phone + laptop to cart
│     │  ├─ DeleteSpecificItemTest.java       # Delete one item & verify total
│     │  └─ CartPersistenceOnRefreshTest.java # Validate cart persistence
│     │
│     └─ checkout/
│        ├─ PlaceOrderTest.java               # Successful order placement
│        └─ CheckoutNegativeTest.java         # Checkout with missing fields
│
└─ reports/                                   # Generated test reports
   ├─ extent/
   │  └─ ExtentReport.html                    # Rich HTML report
   ├─ surefire/                               # Maven Surefire reports
   └─ test-results.xlsx                       # Excel report (Apache POI)
```

---

## 🔄 Test Execution Flow

1. **Authentication**  
   - Signup → Login → Logout  
   - Negative login cases (duplicate signup, empty fields, wrong password)

2. **Product Check**  
   - Validate product details page

3. **Cart Operations**  
   - Add to cart  
   - Delete item & validate totals  
   - Persistence across refresh

4. **Checkout**  
   - Place valid order  
   - Negative checkout flow

---

## 📊 Test Case Summary

| **Test Case**                  | **Type**      | **Description**                                |
|--------------------------------|---------------|------------------------------------------------|
| SignupLoginTest                 | Positive      | Create new user and login                      |
| SignupLoginLogoutTest           | Positive      | Login and logout flow                          |
| ProductDetailsTest              | Positive      | Validate product details page                  |
| AddToCartTest                   | Positive      | Add phone + laptop to cart                     |
| DeleteSpecificItemTest          | Positive      | Delete one item and verify total               |
| CartPersistenceOnRefreshTest    | Positive      | Validate cart persistence after refresh        |
| PlaceOrderTest                  | Positive      | Successful checkout and order placement        |
| DuplicateSignupTest             | Negative      | Validate duplicate user signup fails           |
| EmptyLoginValidationTest        | Negative      | Login with empty fields                        |
| WrongPasswordTest               | Negative      | Login with incorrect password                  |
| CheckoutNegativeTest            | Negative      | Attempt checkout with missing fields           |

📌 **Total Test Cases: 11 → Positive: 7 | Negative: 4**

---

## ⚙️ How to Run

### From IDE
- Import project as **Maven Project**  
- Right-click `testng.xml` → **Run As → TestNG Suite**

### From CLI
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### From Jenkins (CI/CD)
- Configure a Maven job in Jenkins  
- Run:  
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```
- Archive artifacts:  
```
reports/extent/ExtentReport.html
reports/test-results.xlsx
```

---

## 🌐 Cross-Browser Testing
- `testng.xml` defines `<parameter name="browser" value="chrome"/>` (can be switched to `firefox`).  
- `BaseTest` reads the `browser` parameter and launches the correct driver using **WebDriverManager**.

---

## 📊 Reporting
- **ExtentReports (HTML):**  
  Located at `reports/extent/ExtentReport.html`  
  Provides interactive UI with step-level logs, screenshots, and graphs.

- **Excel Reports (Apache POI):**  
  Located at `reports/test-results.xlsx`  
  Captures each test case with timestamp, class, method, status, duration, and error message (if failed).

Both reports can be archived in Jenkins builds and linked to Jira tickets for traceability.

---

## 🙋 Author
**Rohan Chauhan**  
Superset ID: **4625383**  
Java Selenium Batch: **03**  
Capstone project for Wipro NGA Training
