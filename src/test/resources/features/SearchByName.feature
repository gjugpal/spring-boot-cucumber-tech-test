Feature: Search By Name

  @smoke
  Scenario: Search using a valid university name should return a correct match
    Given I search for a university by the name "Aston University"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 1 university
    And the response should contain the university "Aston University"

  Scenario: Search using a common university name should return multiple matches
    Given I search for a university by the name "Sheffield"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 2 universities
    And the response should contain universities with names that contain "Sheffield" only

  Scenario: Search using camel case should return a correct match
    Given I search for a university by the name "shEffIELD"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 2 universities
    And the response should contain universities with names that contain "Sheffield" only

  Scenario: Search using a name which does not exist should return no matches
    Given I search for a university by the name "Gurdeep"
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 0 universities

  @limit
  Scenario: Search using the limit parameter should restrict the number of universities returned
    Given I search for a university by the name "London"
    And I set the search limit to 3
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 3 universities
    And the response should contain universities with names that contain "London" only

  @offset
  Scenario: Search using the offset parameter should return a subset of universities
    Given I search for a university by the name "London"
    And I send the request
    Then the response code should be 200 "OK"
    When I set the offset to 5
    And I send the request
    Then the response code should be 200 "OK"
    Then the second response should return the same universities apart from the first 5

  Scenario: Search using a trailing white space should ignore the white space and return a correct match
    Given I search for a university by the name "Aston "
    When I send the request
    Then the response code should be 200 "OK"
    And the response should return 1 university
    And the response should contain the university "Aston University"
    