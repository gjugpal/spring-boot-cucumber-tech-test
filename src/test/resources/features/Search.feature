Feature: Search

  Scenario: Searching without any query parameters returns all universities from all countries
    Given I perform a search without any query parameters
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain universities from multiple countries

  Scenario: Sending a request to an invalid endpoint returns a 404 Not Found error
    Given I perform a search without any query parameters
    And I set the endpoint to "/random"
    When I send the request
    Then the response code should be 404 "Not Found"