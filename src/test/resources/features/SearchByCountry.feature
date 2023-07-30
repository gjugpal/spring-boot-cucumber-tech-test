Feature: Search By Country

  @smoke
  Scenario: Searching for universities in a specific country should return only those universities in that country
    Given I search for universities in the country "United Kingdom"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain the university "Aston University"
    And the response should not contain the university "Harvard"

  Scenario: Searching for universities by country and using a non-existing country should return no matches
    Given I search for universities in the country "Gurdeep"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 0 universities

  @limit
  Scenario: Setting the return limit restricts the number of universities returned when querying by country
    Given I search for universities in the country "United Kingdom"
    And I set the search limit to 5
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 5 universities
    And the response should contain universities from "United Kingdom" only

  @offset
  Scenario: Setting the offset parameter returns a subset of universities starting with the offset value
    Given I search for universities in the country "United Kingdom"
    And I send the request
    When I set the offset to 3
    And I send the request
    Then the response code should be 200 "OK"
    Then the second response should return the same universities apart from the first 2
