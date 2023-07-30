Feature: Search By Name

  @smoke
  Scenario: Searching by name for a university should return exact match
    Given I search for a university by the name "Aston University"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 1 university
    And the response should contain the university "Aston University"

  Scenario: Searching by name for a university when multiple universities contain that name returns multiple universities
    Given I search for a university by the name "Sheffield"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 2 universities
    And the response should contain universities with names that contain "Sheffield" only

  Scenario: Searching for a university which does not exist should return no matches
    Given I search for a university by the name "Gurdeep"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 0 universities

  @limit
  Scenario: Setting the return limit restricts the number of universities returned when querying by name
    Given I search for a university by the name "London"
    And I set the search limit to 3
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 3 universities
    And the response should contain universities with names that contain "London" only

  @offset
  Scenario: Setting the offset parameter returns a subset of universities starting with the offset value
    Given I search for a university by the name "London"
    And I send the request
    Then the response code should be 200 "OK"
    When I set the offset to 5
    And I send the request
    Then the response code should be 200 "OK"
    Then the second response should return the same universities apart from the first 5
    
  Scenario: Searching for a university by a name which includes a trailing white space should ignore the white space
    Given I search for a university by the name "Aston "
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 1 university
    And the response should contain the university "Aston University"
    