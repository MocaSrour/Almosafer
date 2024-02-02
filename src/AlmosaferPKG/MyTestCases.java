package AlmosaferPKG;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {
	
	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get("https://global.almosafer.com/en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement popUpMsg = driver.findElement(By.cssSelector(".sc-iBmynh.izXFRL"));
		
		if (popUpMsg.isDisplayed()) {
			WebElement sarBtn = driver
					.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
			sarBtn.click();
		}
	}

	@Test(enabled = false)
	public void LanguageEN() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		String expectedLanguage = "EN";
		assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(enabled = false)
	public void Currency() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String expectedCurrency = "SAR";
		assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(enabled = false)
	public void ContactNumber() {
		String actualPhoneNumber = driver.findElement(By.tagName("footer"))
				.findElement(By.cssSelector(".sc-dxgOiQ.hTjMfW.col-lg-12")).getText();
		String expectedPhoneNumber = "920000997";
		assertEquals(actualPhoneNumber.contains(expectedPhoneNumber), true );
		
		String expectedWtsappNumber = "+966554400000";
		String wtsappNumber = driver.findElement(By.tagName("strong")).getText();
		assertEquals(wtsappNumber, expectedWtsappNumber);
	}
	
	@Test(enabled = false)
	public void QitafLogo () {
		WebElement qitafLogoInFooter = driver.findElement(By.tagName("footer")).findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		assertEquals(qitafLogoInFooter.isDisplayed(), true);
	}
	
	@Test(enabled = false)
	public void hotelsTab () {
		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		boolean hotelsTabSelected = Boolean.parseBoolean(hotelsTab.getAttribute("aria-selected"));
		assertEquals(hotelsTabSelected, false);
	}
	
	@Test(enabled = false)
	public void DepartureDate() {
		String departureDate = driver.findElement(By.xpath("//div[@data-testid='FlightSearchBox__FromDateButton']")).getText();
		System.out.println(departureDate);
		System.out.println(LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MMMM dd")));
	}
	
	@Test
	public void RandomLanguage () {
		Random rnd = new Random();
		int rndWebsiteLang = rnd.nextInt(websites.length);
		
		driver.get(websites[rndWebsiteLang]);
		
		String expectedLang = "";
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		
		if(driver.getCurrentUrl().endsWith("ar")) {
			expectedLang = "ar";
		} else {
			expectedLang ="en";
		}
		
		assertEquals(actualLang, expectedLang);
	}
}

