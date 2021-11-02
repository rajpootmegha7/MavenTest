
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import mainclasses.DataProviderClass;

public class SafariTestTestNG {
	public static WebDriver driver;
	WebElement element;
	WebDriverWait wait;

	@BeforeSuite
	public void instantiate() {
		driver = new SafariDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@Test(priority = 1)
	public void navigateTest() throws InterruptedException {
		driver.get("https://quacs.org/spring2022/#/");
		// Implicit wait command
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		String expectedTitle = "QuACS";
		String originalTitle = driver.getTitle();
		Assert.assertEquals(originalTitle, expectedTitle, "Title doesn't match.");

	}

	@Test(priority = 2)
	public void TestLabel() throws InterruptedException {

		element = driver.findElement(By.xpath("//a/span[contains(text(),'ARCH')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		// Using this implicit wait so that you can view page before driver get close.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "SearchProvider", dataProviderClass = DataProviderClass.class)
	public void testMethod(String author, String searchKey) throws InterruptedException {
		driver.get("https://quacs.org/spring2022/#/");
		// Page load timeout it will wait for page to load.
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Search text in Quacs text box

		WebElement searchText = driver.findElement(By.xpath("//input[@id='search-bar']"));
		wait.until(ExpectedConditions.visibilityOf(searchText));

		searchText.sendKeys(searchKey);
		System.out.println("Welcome ->" + author + " Your search key is->" + searchKey);

		// get text from search box
		String testValue = searchText.getAttribute("value");
		System.out.println(testValue + "::::" + searchKey);
		// Using this implicit wait so that you can view page before driver get close.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		searchText.clear();
		// verify if search box has correct value
		Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}

	@AfterSuite
	public void afterMethod() {
		driver.close();
		System.out.println("Finished Test On Safari Browser");
	}

}
