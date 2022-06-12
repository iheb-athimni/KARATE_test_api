@testAll
Feature: feature to test authentication page

  Scenario: check First API test
    Given url "https://reqres.in/api/users"
    And params name = "morpheus"
    And params job = "leader"
    When method POST
    Then status 201