package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static browsers.ChromeBrowser.configuredChromeBrowser;
import static pages.BasePage.Locator.SUBMIT_BUTTON;

@Slf4j
public abstract class BasePage {

@AllArgsConstructor
@Getter
public enum Locator {
    SUBMIT_BUTTON(By.cssSelector("button[type='submit']"));
    
    private final By by;
}

public static final String PAGE_URL = "https://allegro.pl/";
private static final int TIMEOUT_INT_SECONDS = 5000;
private WebDriver driver;

public BasePage() {
    driver = configuredChromeBrowser();
}

public abstract String getPageTitle();

public WebElement getWebElement(By locator) {
    waitForToBeClickable(driver.findElement(locator));
    waitForVisibility(locator);
    return this.driver.findElement(locator);
}

public List<WebElement> getWebElements(By locator) {
    waitForToBeClickable(driver.findElement(locator));
    waitForVisibility(locator);
    return this.driver.findElements(locator);
}

public void load() {
    load(PAGE_URL);
}

private void load(String pageUrl) {
    this.driver.get(PAGE_URL);
    Alert alert = ExpectedConditions.alertIsPresent().apply(this.driver);
    if (alert != null) {
        this.driver.switchTo().alert().accept();
    }
    log.info("Navigated to {}", pageUrl);
}

public String getCurrentPageUrl() {
    return this.driver.getCurrentUrl();
}

public void selectFromDropdownListByValue(WebElement element, String value) {
    waitForVisibility(element);
    Select select = new Select(element);
    select.selectByValue(value);
}

public void type(WebElement element, String text) {
    waitForToBeClickable(element);
    clickElement(element);
    element.clear();
    element.sendKeys(text);
}

public void clickSubmitButton() {
    clickElement(getWebElement(SUBMIT_BUTTON.getBy()));
}

public void clickOnCheckboxButton(WebElement element) {
    waitForVisibility(element);
    waitForToBeClickable(element);
    if (!(element).isSelected()) {
        selectCheckBox(element);
    }
}

public void moveToElement(WebElement element) {
    waitForVisibility(element);
    waitForToBeClickable(element);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
}

public void waitForSpinnerToDisappear(WebElement spinner) {
    WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_INT_SECONDS);
    wait.until(ExpectedConditions.visibilityOf(spinner));
    wait.until(ExpectedConditions.invisibilityOf(spinner));
}

private void clickElement(WebElement element) {
    waitForVisibility(element);
    element.click();
}

private void selectCheckBox(WebElement element) {
    if (!element.isSelected()) {
        clickOnElementWithJsExecutor(element);
    }
}

public void quitDriver() {
    this.driver.quit();
}

public String getTextFromWebElement(WebElement element) {
    return element.getText();
}

private void clickOnElementWithJsExecutor(WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) this.driver;
    Actions actions = new Actions(this.driver);
    actions.moveToElement(element).perform();
    executor.executeScript("arguments[0].click();", element);
}

private void waitForToBeClickable(WebElement element) {
    (new WebDriverWait(this.driver, TIMEOUT_INT_SECONDS)).until(ExpectedConditions.elementToBeClickable(element));
}

private void waitForVisibility(WebElement element) {
    (new WebDriverWait(this.driver, TIMEOUT_INT_SECONDS)).until(ExpectedConditions.visibilityOf(element));
}

private void waitForVisibility(By locator) {
    (new WebDriverWait(this.driver, TIMEOUT_INT_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(locator));
}
}
