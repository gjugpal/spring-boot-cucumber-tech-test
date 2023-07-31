Feature: Search By Country

  @smoke
  Scenario: Search using a valid country should return only universities in that country
    Given I search for universities in the country "United Kingdom"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain the university "Aston University"
    And the response should not contain the university "Harvard"

  Scenario: Search using a non-existing country should return no matches
    Given I search for universities in the country "Gurdeep"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 0 universities

  @limit
  Scenario: Search using the limit parameter should restrict the number of universities returned
    Given I search for universities in the country "United Kingdom"
    And I set the search limit to 5
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 5 universities
    And the response should contain universities from "United Kingdom" only

  @offset
  Scenario: Search using the offset parameter should return a subset of universities
    Given I search for universities in the country "United Kingdom"
    And I send the request
    When I set the offset to 3
    And I send the request
    Then the response code should be 200 "OK"
    Then the second response should return the same universities apart from the first 3
