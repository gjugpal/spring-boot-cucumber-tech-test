Feature: Search By Name and Country


  Scenario: Search for a university in a country where it does not exist returns no matches
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

  Scenario: Search using a valid name but empty country value ignores the country value and returns based on name only
    Given I search for university "University" in the country ""
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain universities from multiple countries
  
  Scenario: Search using an empty name and a valid country ignores the name value and returns based on country only
    Given I search for university "" in the country "United Kingdom"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should contain universities from "United Kingdom" only