Feature: Search By Name and Country


  Scenario: Searching for a university in a specific country which does not exist returns no matches
    Given I search for university "Aston University" in the country "India"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 0 universities

  @smoke
  Scenario: Search for a university by both name and country returns exact match
    Given I search for university "Aston University" in the country "United Kingdom"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 1 university
    And the response should contain the university "Aston University"