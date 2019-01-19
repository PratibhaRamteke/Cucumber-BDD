package tests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	public WebDriver driver;
	
  @Test
  public void openMyBlog() {
	  driver.get("https://www.google.co.in/");
  }
  
  @BeforeClass
  public void beforeClass() {
	  String driverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", driverPath);
	  driver = new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
