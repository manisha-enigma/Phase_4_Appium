package Amazon;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class SearchEarPhones {
	static 	AndroidDriver<MobileElement> driver;
	
  @BeforeClass
  public void beforeClass() throws InterruptedException, MalformedURLException {
	 // this.driver=driver;
	  System.out.println("Driver Configuratios");
		//APPIUM Server Details
		URL url = new URL ("http://127.0.0.1:4723/wd/hub");	
		System.out.println("URL : " + url);
		//Declaring Desired Capabilities
		DesiredCapabilities cap = new DesiredCapabilities() ;
		//Setting Desired Capabilities
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion","12");
		//cap.setCapability("appActivity","com.touchboarder.androidapidemos.MainActivity");	
		cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		//Driver creation
		driver = new AndroidDriver<MobileElement>(url, cap);
		System.out.println("Driver Details" + driver + "\n");
		SessionId sesId = driver.getSessionId();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.print("Session Id = " + sesId + "\n");
		Thread.sleep(4000);  
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Select English\"]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//android.widget.Button[@text='Continue in English']")).click();
		Thread.sleep(3000);  
		driver.findElement(By.xpath("//android.widget.Button[@text='Skip sign in']")).click();
		Thread.sleep(2000);  
		driver.hideKeyboard();
  }
  
  @Test
  public void SearchEarPhonesWithMic() throws InterruptedException {
		
		Thread.sleep(3000);  
		driver.findElement(By.xpath("//android.widget.EditText")).click();
		Thread.sleep(1000); 
		MobileElement search = driver.findElement(By.xpath("//android.widget.EditText"));
		search.sendKeys("earphones with ");
		//search.click();
		driver.hideKeyboard();
		Thread.sleep(1000); 
		clickView("earphones with microphone");
		Thread.sleep(1000); 
	}
  
  
  @Test
  public void listSearchResult() throws InterruptedException {
	  clickviewView("boAt Bassheads 242 in Ear Wired Earphones with Mic(Active Black)");
	  Thread.sleep(1000); 
	}
  
  
  private static void clickView(String elemClick) throws InterruptedException {
	  Thread.sleep(1000);
	  List<MobileElement> lstViews = driver.findElements(By.xpath("//android.widget.LinearLayout/android.widget.TextView"));
	  	Thread.sleep(1000); 
	  	for (MobileElement view : lstViews) {
			//System.out.println(view.getText());
			Thread.sleep(1000); 
			if (view.getAttribute("text").equals(elemClick)) {
				System.out.println("\n MATCHED TEXT IS '" + view.getText() + "'\n");
				Thread.sleep(1000); 
				view.click();
				Thread.sleep(1000);
				break;
			}
		}
	}
   
  private static void clickviewView(String elemClick) throws InterruptedException {
	  Thread.sleep(1000);
	  List<MobileElement> lstViews = driver.findElements(By.xpath("//android.view.View"));
	  	Thread.sleep(1000); 
	  	for (MobileElement view : lstViews) {
			//System.out.println(view.getText());
			Thread.sleep(1000); 
			if (view.getAttribute("text").equalsIgnoreCase(elemClick)) {
				System.out.println("\n MATCHED TEXT IS '" + view.getText() + "'\n");
				Thread.sleep(1000); 
				view.click();
				break;
			}
			System.out.println("\n MATCHED TEXT IS '" + view.getText() + "'\n");
		}
	}

  

  @AfterTest
  public void tearDown() throws Exception {
	//close the app
      driver.quit();
	}

}

