Feature: Validating Pet API's

  Scenario Outline: Verify that Pet is added successfully using petAPI
    Given Pet is created with "<id>" "<name>" "<status>"
    When user calls "petAPI" with "POST" https request
    Then the API call got success with status code 200
    And "status" in response body is "<status>"
    And "name" in response body is "<name>"

    Examples:
      | id   | name    | status           |
      | 4444 | TestDog | AvailableForTest |


  Scenario Outline: Verify that Pet is deleted successfully using petAPI
    Given there is pet with "<id>"
    When user calls "petAPI" with "Delete" https request
    Then the API call got success with status code 200
    When user calls "petAPI" with "GET" https request
    Then "message" in response body is "<message>"

    Examples:
      | id   | message       |
      | 4444 | Pet not found |


	
	

	
	
	