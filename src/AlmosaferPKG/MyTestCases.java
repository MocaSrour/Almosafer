package AlmosaferPKG;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

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

	@Test(priority = 1)
	public void LanguageEN() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		String expectedLanguage = "EN";
		assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(priority = 2)
	public void Currency() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String expectedCurrency = "SAR";
		assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void ContactNumber() {
		String actualPhoneNumber = driver.findElement(By.tagName("footer"))
				.findElement(By.cssSelector(".sc-dxgOiQ.hTjMfW.col-lg-12")).getText();
		String expectedPhoneNumber = "920000997";
		assertEquals(actualPhoneNumber.contains(expectedPhoneNumber), true);

		String expectedWtsappNumber = "+966554400000";
		String wtsappNumber = driver.findElement(By.tagName("strong")).getText();
		assertEquals(wtsappNumber, expectedWtsappNumber);
	}

	@Test(priority = 4)
	public void QitafLogo() {
		WebElement qitafLogoInFooter = driver.findElement(By.tagName("footer"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		assertEquals(qitafLogoInFooter.isDisplayed(), true);
	}

	@Test(priority = 5)
	public void hotelsTab() {
		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		boolean hotelsTabSelected = Boolean.parseBoolean(hotelsTab.getAttribute("aria-selected"));
		assertEquals(hotelsTabSelected, false);
	}

	@Test(priority = 6)
	public void DepartureDate() {
		String day = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();
		String month = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-bYnzgO doqpRk']"))
				.getText();
		driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.click();
		String year = driver.findElement(By.cssSelector(".sc-jDwBTQ.sc-gPEVay.htcLFw")).getText();

		String departureDate = year + " " + month + " " + day;
		String tomorrowDate = LocalDate.now().plusDays(1)
				.format(java.time.format.DateTimeFormatter.ofPattern("YYYY MMMM dd"));

		assertEquals(departureDate, tomorrowDate);
	}

	@Test(priority = 7)
	public void ReturnDate() throws InterruptedException {
		String day = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();
		String month = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-bYnzgO doqpRk']"))
				.getText();
		driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.click();
		String year = driver
				.findElement(By
						.cssSelector("div[class='sc-cMljjf sc-jAaTju lEldS'] span[class='sc-jDwBTQ sc-gPEVay htcLFw']"))
				.getText();

		String returnDate = year + " " + month + " " + day;
		String afterTomorrowDate = LocalDate.now().plusDays(2)
				.format(java.time.format.DateTimeFormatter.ofPattern("YYYY MMMM dd"));

		assertEquals(returnDate, afterTomorrowDate);
	}

	@Test(priority = 8)
	public String RandomLanguage() {
		Random rnd = new Random();
		int rndWebsiteLang = rnd.nextInt(websites.length);

		driver.get(websites[rndWebsiteLang]);

		String expectedLang = "";
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

		if (driver.getCurrentUrl().endsWith("ar")) {
			expectedLang = "ar";
		} else {
			expectedLang = "en";
		}

		assertEquals(actualLang, expectedLang);
		return actualLang;
	}

	@Test(priority = 9)
	public void RandomCity() throws InterruptedException {
		String lang = RandomLanguage();
		Thread.sleep(2000);
		driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();
		WebElement cityInput = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));
		if (lang == "en") {
			cityInput.sendKeys(citiesEN[rndCitiesEN]);
		} else {
			cityInput.sendKeys(citiesAR[rndCitiesAR]);

		}
		driver.findElement(By.xpath("//ul[@data-testid='AutoCompleteResultsList']"))
				.findElement(By.xpath("//li[@data-testid='AutoCompleteResultItem0']")).click();
	}

	@Test(priority = 10)
	public void RandomRoom() {
		WebElement selectList = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select roomSelector = new Select(selectList);
		Random rndRoom = new Random();
		roomSelector.selectByIndex(rndRoom.nextInt(2));
	}
	
	@Test(priority = 11)
	public void ClickSearchHotels () {
		WebElement searchBtn = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchBtn.click();
	}
	
	@Test(priority = 12)
	public void LoadSearchResultPage () throws InterruptedException {
		Thread.sleep(25000);
		String loadedMsg = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();

		assertEquals(Character.isDigit(loadedMsg.charAt(0)), true);
	}
	
	@Test(priority = 13)
	public void LowestHighestPrices() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']")).click();
		List<WebElement> prices = driver.findElement(By.xpath("//div[@data-testid='HotelSearchResult__ResultsList']")).findElements(By.className("Price__Value"));
		int lowestP = Integer.parseInt(prices.get(0).getText());
		int highestP = Integer.parseInt(prices.get(prices.size() - 1).getText());
		assertEquals(lowestP < highestP, true);
	}
}
