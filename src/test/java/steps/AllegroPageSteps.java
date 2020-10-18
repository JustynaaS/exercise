package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AllegroPage;

public class AllegroPageSteps {

AllegroPage allegroPage = new AllegroPage();

@Given("^The customer visits allegro page$")
public void theCustomerVisitsAllegroPage() {
    allegroPage.load();
    allegroPage.verifyCurrentPageUrl();
    allegroPage.acceptTermsAndConditions();
}

@When("^The customer inputs value in search field$")
public void theCustomerInputsValueInSearchField() {
    allegroPage.verifyPageTitle();
    allegroPage.enterAndSubmitValue();
    allegroPage.verifySearchingValue();
}

@And("^The customer chooses black color from filter section$")
public void theCustomerChoosesBlackColorFromFilterSection() {
    allegroPage.selectAppleDevice();
    allegroPage.selectBlackColor();
    allegroPage.sortElementsByPrice();
}

@Then("^The customer receives total amount of phones on the first page$")
public void theCustomerSeesTotalAmountOfPhonesOnTheFirstPage() {
    allegroPage.showNumbersOfElementsOnPage();
}

@And("^The customer receives the most expensive phone$")
public void theCustomerReceivesTheMostExpensivePhone() {
    allegroPage.getMaxPriceValue();
    allegroPage.quitPage();
}
}
