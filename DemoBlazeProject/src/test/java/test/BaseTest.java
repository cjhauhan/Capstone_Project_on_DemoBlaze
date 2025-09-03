package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class BaseTest {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	protected static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
	protected static ExtentReports extent;

	@BeforeSuite(alwaysRun = true)
	public void startReport() throws Exception {
		Path reportDir = Path.of("reports", "extent");
		Files.createDirectories(reportDir);

		ExtentSparkReporter spark = new ExtentSparkReporter(reportDir.resolve("ExtentReport.html").toString());
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("DemoBlaze Capstone Report");
		spark.config().setReportName("Automation Suite");

		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

//    @Parameters({"browser"})
//    @BeforeMethod(alwaysRun = true)
//    public void setUp(@Optional("chrome") String browser, Method method) {
//        WebDriver driver;
//
//        try {
//            if (browser.equalsIgnoreCase("edge")) {
//                WebDriverManager.edgedriver().setup();
//                EdgeOptions options = new EdgeOptions();
//                options.addArguments("--start-maximized");
//                driver = new EdgeDriver(options);
//            } else {
//            	WebDriverManager.edgedriver().browserVersion("139.0.3405.125").setup();
//            	EdgeOptions options = new EdgeOptions();
//            	options.addArguments("--start-maximized");
//            	driver = new EdgeDriver(options);
//
//            }
//
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//            tlDriver.set(driver);
//
//            // create Extent test after extent is initialized
//            if (extent != null) {
//                ExtentTest test = extent.createTest(method.getDeclaringClass().getSimpleName() + "::" + method.getName());
//                tlTest.set(test);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser, e);
//        }
//    }
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(@Optional("chrome") String browser, Method method) {
		System.out.println(">>> Starting setup for browser: " + browser); // debug

		WebDriver driver;
		try {
			if (browser.equalsIgnoreCase("edge")) {
				System.out.println(">>> Using manually downloaded EdgeDriver...");
				System.setProperty("webdriver.edge.driver",
						"C:\\Users\\hp\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--start-maximized");
				driver = new EdgeDriver(options);
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(">>> Setting up FirefoxDriver...");
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized");
				driver = new FirefoxDriver(options);
			} else {
				System.out.println(">>> Setting up ChromeDriver...");
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			tlDriver.set(driver);

			if (extent != null) {
				ExtentTest test = extent
						.createTest(method.getDeclaringClass().getSimpleName() + "::" + method.getName());
				tlTest.set(test);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser, e);
		}
	}

	protected void logPageTitle() {
		String title = getDriver().getTitle();
		getTest().info("Page Title: " + title);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		ExtentTest test = tlTest.get();
		if (test != null) {
			switch (result.getStatus()) {
			case ITestResult.SUCCESS -> test.pass("Test passed");
			case ITestResult.FAILURE -> test.fail(result.getThrowable());
			case ITestResult.SKIP -> test.skip("Test skipped");
			}
		}

		if (getDriver() != null) {
			getDriver().quit();
		}

		tlDriver.remove();
		tlTest.remove();
	}

	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	protected WebDriver getDriver() {
		return tlDriver.get();
	}

	protected ExtentTest getTest() {
		return tlTest.get();
	}
}
