Feature: Search

  Scenario: Search without any query parameters returns all universities from all countries
    Given I perform a search without any query parameters
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain universities from multiple countries

  Scenario: Send request to an invalid endpoint returns a 404 Not Found error
    Given I perform a search without any query parameters
    And I set the endpoint to "/random"
    When I send the request
    Then the response code should be 404 "NOT FOUND"

  Scenario: Search using an invalid limit query parameter returns a 500 Internal Server Error
    Given I perform a search without any query parameters
    And I set the endpoint to "/search?limit=a"
    When I send the request
    Then the response code should be 500 "INTERNAL SERVER ERROR"

    Scenario: Search using an invalid offset query parameter returns a 500 Internal Server Error
    Given I perform a search without any query parameters
    And I set the endpoint to "/search?offset=a"
    When I send the request
    Then the response code should be 500 "INTERNAL SERVER ERROR"
