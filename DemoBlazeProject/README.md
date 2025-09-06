# Demoblaze Capstone Project 🚀

Automation framework built on the [Demoblaze](https://www.demoblaze.com/) demo e-commerce site.  
Covers **signup, login, product browsing, cart operations, and checkout** using Selenium + TestNG.

---

## 📌 Overview
This project demonstrates:
- Professional **Page Object Model (POM)** design
- Robust **TestNG framework** integration
- **Cross-browser testing** with Chrome & Firefox
- **ExtentReports** for reporting

---

## 🛠️ Tech Stack
- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** ExtentReports  

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
│  └─ test/java/com/demoblaze/tests
│     ├─ base/
│     │  └─ BaseTest.java
│     ├─ auth/
│     │  ├─ SignupLoginTest.java              # Create new user & login
│     │  ├─ SignupLoginLogoutTest.java        # Login & logout flow
│     │  ├─ DuplicateSignupTest.java          # Validate duplicate user signup
│     │  ├─ EmptyLoginValidationTest.java     # Login with empty fields
│     │  └─ WrongPasswordTest.java            # Login with wrong password
│     ├─ product/
│     │  └─ ProductDetailsTest.java           # Validate product details
│     ├─ cart/
│     │  ├─ AddToCartTest.java                # Add phone + laptop to cart
│     │  ├─ DeleteSpecificItemTest.java       # Delete one item & verify total
│     │  └─ CartPersistenceOnRefreshTest.java # Validate cart persistence
│     └─ checkout/
│        ├─ PlaceOrderTest.java               # Successful order placement
│        └─ CheckoutNegativeTest.java         # Checkout with missing fields
│
└─ reports/                                   # Generated test reports
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

## ⚙️ How to Run

### From IDE
- Import project as **Maven Project**  
- Right-click `testng.xml` → **Run As → TestNG Suite**

### From CLI
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 🌐 Cross-Browser Testing
- `testng.xml` defines two `<test>` blocks:
  - One for **Chrome**
  - One for **Firefox**
- `BaseTest` reads the `browser` parameter and launches the correct driver.

---

## 📊 Reporting
- Reports generated using **ExtentReports**  
- Location: `test-output/ExtentReports.html`  
- Provides pass/fail summary, screenshots (if configured), and logs  

---

## 🙋 Author
**Rohan Chauhan**  
Capstone project for Wipro NGA Training
