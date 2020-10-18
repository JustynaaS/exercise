$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AllegroPage.feature");
formatter.feature({
  "line": 1,
  "name": "Allegro page scenarios",
  "description": "",
  "id": "allegro-page-scenarios",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Find the most expensive iPhone",
  "description": "",
  "id": "allegro-page-scenarios;find-the-most-expensive-iphone",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "The customer visits allegro page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "The customer inputs value in search field",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "The customer chooses black color from filter section",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "The customer receives total amount of phones on the first page",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "The customer receives the most expensive phone",
  "keyword": "And "
});
formatter.match({
  "location": "AllegroPageSteps.theCustomerVisitsAllegroPage()"
});
formatter.result({
  "duration": 14397862700,
  "status": "passed"
});
formatter.match({
  "location": "AllegroPageSteps.theCustomerInputsValueInSearchField()"
});
formatter.result({
  "duration": 4625491100,
  "status": "passed"
});
formatter.match({
  "location": "AllegroPageSteps.theCustomerChoosesBlackColorFromFilterSection()"
});
formatter.result({
  "duration": 18661359500,
  "status": "passed"
});
formatter.match({
  "location": "AllegroPageSteps.theCustomerSeesTotalAmountOfPhonesOnTheFirstPage()"
});
formatter.result({
  "duration": 312614800,
  "status": "passed"
});
formatter.match({
  "location": "AllegroPageSteps.theCustomerReceivesTheMostExpensivePhone()"
});
formatter.result({
  "duration": 12973409500,
  "status": "passed"
});
});