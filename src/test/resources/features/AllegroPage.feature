Feature: Allegro page scenarios

  Scenario: Find the most expensive iPhone
    Given The customer visits allegro page
    When The customer inputs value in search field
    And The customer chooses black color from filter section
    Then The customer receives total amount of phones on the first page
    And The customer receives the most expensive phone
