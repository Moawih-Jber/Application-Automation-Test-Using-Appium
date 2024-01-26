
import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import java.util.List;

public class AppiumApplicationTest {
	// this is what we do in selenium - for the web testing

	// WebDriver driver = new ChromeDriver();

	// this is what we do for the appium for mobile testing
	// we will use them always
	DesiredCapabilities caps = new DesiredCapabilities();
	AndroidDriver driver;

	@BeforeTest
	public void TheSetUp() {
//we will use them always
//import java.net.URL;
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Moawih");

		File myapp = new File("src/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myapp.getAbsolutePath());

	}

	@Test(enabled = false)
	public void myTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
		String result = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();

		Assert.assertEquals(result, "45");
	}

	@Test(enabled = false)
	public void ClickOnAllnumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {
				allButtons.get(i).click();
			} else {
				continue;
			}
		}

	}

	@Test()
	public void clickonevenNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

//			if (allButtons.get(i).getAttribute("resource-id").contains("digit_2")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_4")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_6")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_8")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_0")) {
//				allButtons.get(i).click();
//			} else {
//				continue;
//			}

			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {

				int number = Integer.parseInt(allButtons.get(i).getAttribute("content-desc"));

				if (number % 2 == 0) {
					System.out.println(number);
					allButtons.get(i).click();
				}
			}

		}
	}

	@AfterTest
	public void MyPostTest() {

	}

}
