package Configuration;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfig {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public void setup(String browser) {
		if (driver.get() == null) {
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				driver.set(new ChromeDriver(chromeOptions));
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver());
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
				break;
			default:
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void teardown() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}