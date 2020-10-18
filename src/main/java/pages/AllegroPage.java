package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static pages.AllegroPage.Locator.APPLE_DEVICE;
import static pages.AllegroPage.Locator.BLACK_COLOR_BUTTON;
import static pages.AllegroPage.Locator.IPHONE_11_CATEGORY;
import static pages.AllegroPage.Locator.LIST_OF_ELEMENTS;
import static pages.AllegroPage.Locator.PHONE_PRICE;
import static pages.AllegroPage.Locator.SEARCH_FIELD;
import static pages.AllegroPage.Locator.SEARCH_VALUE_HEADER;
import static pages.AllegroPage.Locator.SORT_FIELD;
import static pages.AllegroPage.Locator.SPINNER;
import static pages.AllegroPage.Locator.TERMS_CONTENTS;
import static pages.AllegroPage.Text.PAGE_TITLE;
import static pages.AllegroPage.Text.SEARCHING_VALUE;

public class AllegroPage extends BasePage {

@AllArgsConstructor
@Getter
public enum Locator {
    PAGE_TITLE_HEADER(By.cssSelector("[data-role='header-primary-bar'] h2 > a")),
    TERMS_CONTENTS(By.cssSelector("[data-role='accept-consent']")),
    SEARCH_FIELD(By.cssSelector("[data-prototype-id='allegro.metrumHeader.search'] input[type='search']")),
    SEARCH_VALUE_HEADER(By.cssSelector("h1 > span:nth-of-type(2)")),
    APPLE_DEVICE(By.cssSelector("div [data-key='Elektronika'] + ul  a + ul a")),
    IPHONE_11_CATEGORY(By.cssSelector("[data-role='Categories'] ul > li:first-of-type a")),
    BLACK_COLOR_BUTTON(By.linkText("czarny")),
    SORT_FIELD(By.cssSelector("select[data-value='m']")),
    LIST_OF_ELEMENTS(By.cssSelector("section[class='_9c44d_3pyzl'] > article > div")),
    SPINNER(By.cssSelector("._e219d_19-zf ._6gjm5")),
    PHONE_PRICE(By.cssSelector("div > span._1svub._lf05o"));
    
    private final By by;
}

@AllArgsConstructor
@Getter
public enum Text {
    PAGE_TITLE("Allegro.pl - wygodne i bezpieczne zakupy online, największy wybór ofert"),
    SEARCHING_VALUE("Iphone 11");
    
    private final String textValue;
}

public AllegroPage() {
    super();
}

@Override
public String getPageTitle() {
    return getWebElement(Locator.PAGE_TITLE_HEADER.getBy()).getAttribute("title");
}

public void acceptTermsAndConditions() {
    if (getWebElement(TERMS_CONTENTS.getBy()).isDisplayed()) {
        getWebElement(TERMS_CONTENTS.getBy()).click();
    }
}

public void enterAndSubmitValue() {
    type(getWebElement(SEARCH_FIELD.getBy()), "Iphone 11");
    clickSubmitButton();
}

public void selectAppleDevice() {
    getWebElement(APPLE_DEVICE.getBy()).click();
    getWebElement(IPHONE_11_CATEGORY.getBy()).click();
}

public void selectBlackColor() {
    moveToElement(getWebElement(BLACK_COLOR_BUTTON.getBy()));
    clickOnCheckboxButton(getWebElement(BLACK_COLOR_BUTTON.getBy()));
}

public void sortElementsByPrice() {
    selectFromDropdownListByValue(getWebElement(SORT_FIELD.getBy()), "pd");
    waitForSpinnerToDisappear(getWebElement(SPINNER.getBy()));
}

public void getMaxPriceValue() {
    List<String> phonePrices = new LinkedList<>();
    List<WebElement> elementsOnPage = getWebElements(LIST_OF_ELEMENTS.getBy());
    
    for (WebElement element : elementsOnPage) {
        if (element.getText().toUpperCase().contains("IPHONE")) {
            phonePrices.add(element.findElement(PHONE_PRICE.getBy()).getText());
        }
    }
    System.out.println("max price: " + Collections.max(phonePrices));
}

public void showNumbersOfElementsOnPage() {
    int elementsOnTheFirstPage = getWebElements(LIST_OF_ELEMENTS.getBy()).size();
    System.out.println("Total number of phones on the first page: " + elementsOnTheFirstPage);
}

public void quitPage() {
    quitDriver();
}

public void verifyPageTitle() {
    assertSoftly(softAssertions ->
                     softAssertions.assertThat(getPageTitle()).isEqualTo(PAGE_TITLE.getTextValue()));
}

public void verifyCurrentPageUrl() {
    assertSoftly(softAssertions -> softAssertions.assertThat(getCurrentPageUrl()).isEqualTo(PAGE_URL));
}

public void verifySearchingValue() {
    assertSoftly(softAssertions ->
                     softAssertions.assertThat(getTextFromWebElement(getWebElement(SEARCH_VALUE_HEADER.getBy())))
                         .isEqualTo(SEARCHING_VALUE.getTextValue()));
}
}
