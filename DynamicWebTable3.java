package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicWebTable3 {
	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=login/");
	}

	@Test
	public void learnDynamicWebTable(String String) throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//*[contains(@placeholder, 'ssword') and contains(@type, 'password')]")).sendKeys("abc123");
		driver.findElement(By.xpath("//*[contains(text(), 'ign in') and @name = 'login']")).click();
		driver.findElement(By.xpath("//ul[@id='side-menu']/descendant::span[text() = 'Bank & Cash' ]")).click();

		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav nav-second-level collapse in']/descendant::a[text() = 'New Account' ]")));

		driver.findElement(By.xpath("//ul[@class='nav nav-second-level collapse in']/descendant::a[text() = 'New Account' ]")).click();

		String expectedAccountTitle = "Dynamic Web Table" + new Random().nextInt(999);
		String Balance = "5,000" + new Random().nextInt(999);
		System.out.println("Expected Data " + expectedAccountTitle);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='account']")).sendKeys(expectedAccountTitle);
		driver.findElement(By.xpath("//div[@class='ibox-content']/descendant::input[@id='description']")).sendKeys("Machhapuchhre");
		driver.findElement(By.xpath("//div[@class='form-group']/descendant::input[@id='balance']")).sendKeys(Balance);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text() = 'Transactions']")).click();
		driver.findElement(By.xpath("//a[text() = 'View Transactions']")).click();
		
		
	this.getColumnDataFor(String);
		List<String> columnData = getColumnDataFor("Account");
		Assert.assertTrue(isDiscriptionMatch(expectedAccountTitle.equals(columnData), "The account is not posted"));		
		}
			
	
	private boolean isDiscriptionMatch(boolean equals, String string) {
		return false;
	}

	public 	List<String> getColumnDataFor(String columnHeader) {
			List<String> columnData = new ArrayList<String>();
			int index = getColumnHeaderIndexFor(columnHeader);
			
			String xpath = "//table/descendant::td["+index+"]";
			List<WebElement> columnDataElements = driver.findElements(By.xpath(xpath));
			
			for(int i=0; i<columnDataElements.size(); i++) {
				columnData.add(columnDataElements.get(i).getText());
				System.out.println("column Data  " + columnDataElements.get(i).getText());
			}
			return columnData;
		}
		private int getColumnHeaderIndexFor(String columnHeader) {
			String xpath = "//table[@class='table table-bordered sys_table']/tbody/tr[1]/th";
			List<WebElement> columnHeaderElements = driver.findElements(By.xpath(xpath));
			int index = 1;
			for (WebElement element : columnHeaderElements) {
				if (element.getText().equalsIgnoreCase(columnHeader)) {
					return index;
				}
				index++;
			}
			return 0;
		}
		

		private boolean isDiscriptionMatch(String expectedAccountTitle, List <String> columnData) {
			for (int i = 0; i < columnData.size(); i++) {
				if (expectedAccountTitle.equalsIgnoreCase(columnData.get(i)));
				System.out.println("Actual Data  " + columnData);
				{
					return true;
				}
			}
			return false;
		}	
		
		
		
		
		/*
	 * private boolean isDataPresent(String expectedAccountTitle, List<String>
	 * columnData) { for (int i = 0; i < columnData.size(); i++) { if
	 * (expectedAccountTitle.equalsIgnoreCase(columnData.get(i)));
	 * System.out.println(columnData); { return true; } } return false; }
	 */
}

