@testAll
Feature: feature to test authentication page

  Scenario: check Valid information with chrome
    Given Open the browser
    And I open the Authentication Page
    When User enter the username as "<arg0>"and password as "<arg1>"
    And click on Login
    Then the User Navigate to the result Page
    And Close the browser

  Scenario: check Valid information with back Api
    Given check the API respence
