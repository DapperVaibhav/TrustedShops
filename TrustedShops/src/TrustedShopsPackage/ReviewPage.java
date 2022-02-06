package TrustedShopsPackage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class ReviewPage {
 WebDriver driver;
 
@Test(priority=0)
 public void PageRefresh() throws InterruptedException {
 driver.navigate().refresh();
 }
 
  @Test(priority=1)
  public void VerifyTitle() throws InterruptedException {
	  String expectedTitle= "Bewertungen zu Jalousiescout.de | Lesen Sie 137.877 Bewertungen zu Jalousiescout.de";
	  String actualTitle= driver.getTitle();
	  System.out.println(actualTitle);
	  if(actualTitle.contentEquals(expectedTitle)) {
		  System.out.println("Title Verified");
	  }
	  else {
		  System.out.println("Title Not Matching");
	  }
  }
  
  @Test(priority=2)
  public void Rating() throws InterruptedException {	  
	  List<WebElement> rates = driver.findElements(By.xpath("//div[@class='score-info']"));
	     for(WebElement element1:rates)
	     {
	    	 String overallrating= element1.getText();
	    	 System.out.println(overallrating);
	            if (overallrating == null) {
	            	System.out.println("Rating is zero please check");
	            }
	            else {
	            	System.out.println("Rating is proper");
	            }
	     }
  }
  
 @Test(priority=3)
  public void Hover() throws InterruptedException {
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("window.scrollBy(0,700)");
     Thread.sleep(5000);
     Actions action = new Actions(driver);
     WebElement a= driver.findElement(By.xpath("//span[@class='tsproi tsproi-icon-feedback-info-circle-outline ng-tns-c63-29']"));
     action.moveToElement(a).perform();
     String hover= a.getAttribute("attribute name");
     System.out.println(hover);
     Thread.sleep(2000);

     WebElement cookies = driver.findElement(By.xpath("//button[@id='uc-btn-accept-banner']"));
     if (cookies.isDisplayed()) {
    	 cookies.click();
     }
     else {
    	 System.out.println("No pop up yet");
     }
  }
  
  @Test(priority=4)
  public void OneStarFilter() throws InterruptedException {;
WebDriverWait wait = new WebDriverWait (driver, 20);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='tsproi tsproi-icon-arrow-chevron-down rotate-on-open ng-tns-c53-6']"))).click();

JavascriptExecutor js1 = (JavascriptExecutor) driver;
js1.executeScript("window.scrollBy(0,100)");

WebDriverWait wait1 = new WebDriverWait (driver, 20);
wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[@class='ng-tns-c53-6']"))).get(4).click();

Thread.sleep(2500);
int starExpected= 1;
List<WebElement> onestar = driver.findElements(By.xpath("//span[@class='star tsproi tsproi-star-filled active ng-star-inserted']"));
for(WebElement element3:onestar) {
	String star= element3.getText();
	if(Integer.parseInt(star)>starExpected) {
		System.out.println("Filter is not proper");
	}
	else {
		System.out.println("Filter is working fine");
	}
}

  }

 
 @Test(priority=5)
  public void Sum() throws InterruptedException {  
	 int sum=0;	 
	 List<WebElement> sumpercentage = driver.findElements(By.xpath("//div[@class='bar-value']"));
     for(WebElement element2:sumpercentage)
     {
         String totalsum= element2.getText().replace(" %", "");
         System.out.println(totalsum); 
         sum= sum + Integer.parseInt(totalsum);
     }
     System.out.println(sum);
}
  @BeforeTest
  public void beforeTest() throws InterruptedException {
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\abc\\Desktop\\TrustedShops\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
	    driver.get("https://www.trustedshops.de/bewertung/info_X77B11C1B8A5ABA16DDEC0C30E7996C21.html");
	    driver.manage().window().maximize();
	    Thread.sleep(500);
  }

  @AfterTest
  
  public void afterTest() {
	  driver.close();
  }
}