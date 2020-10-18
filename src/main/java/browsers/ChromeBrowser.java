package browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser extends ChromeDriver {

public ChromeBrowser(ChromeOptions options) {
    super(options);
}

private ChromeBrowser() {
    super();
}

public static ChromeBrowser configuredChromeBrowser() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    ChromeBrowser browser = new ChromeBrowser();
    browser.manage().window().maximize();
    return browser;
}
}
