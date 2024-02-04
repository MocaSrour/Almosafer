package AlmosaferPKG;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	
	Random rndCities = new Random();
	
	String[] websites = { "https://global.almosafer.com/ar", "https://global.almosafer.com/en" };
	
	String[] citiesEN = {"Dubai", "Jeddah", "Riyadh"};
	int rndCitiesEN = rndCities.nextInt(citiesEN.length);
	
	String[] citiesAR = {"دبي","جدة"};
	int rndCitiesAR = rndCities.nextInt(citiesAR.length);

}
