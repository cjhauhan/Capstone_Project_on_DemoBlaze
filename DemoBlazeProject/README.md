# DemoBlaze Capstone Selenium Project

## Overview
This project automates 5 core test cases for the [DemoBlaze](https://www.demoblaze.com) web application using:
- Selenium WebDriver
- TestNG Framework
- ExtentReports for reporting
- WebDriverManager for driver handling

## Test Cases Included
1. **Signup & Login Test**
2. **Signup, Login & Logout Test**
3. **Wrong Password Test**
4. **Add to Cart Test**
5. **Place Order Test**

## Setup Instructions
1. Clone or download the project.
2. Import into **Eclipse** or **IntelliJ** as a Maven project.
3. Run `mvn clean test` or directly run `testng.xml`.

## Cross Browser Testing
Supported browsers:
- Chrome (default)
- Edge

You can change the browser in `testng.xml`:
```xml
<parameter name="browser" value="chrome"/>
```
or
```xml
<parameter name="browser" value="edge"/>
```

## Reports
After execution, an Extent HTML report is generated under:
```
reports/extent/ExtentReport.html
```
