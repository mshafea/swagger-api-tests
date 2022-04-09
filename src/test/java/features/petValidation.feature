Feature: Validating Pet API's

  Scenario Outline: Verify that Pet is added successfully using petAPI
    Given Add Pet Payload with "<id>" "<name>" "<status>"
    When user calls "petAPI" with "POST" https request
    Then the API call got success with status code 200
    And "status" in response body is "<status>"
    And "name" in response body is "<name>"

    Examples:
      | id   | name    | status           |
      | 4444 | TestDog | AvailableForTest |

  @DeletePet
  Scenario Outline: Verify that Pet is deleted successfully using petAPI
    Given Delete Pet Payload
    When user calls "petAPI" with "Delete" https request
    Then the API call got success with status code 200
    When user calls "petAPI" with "GET" https request
    Then "message" in response body is "<message>"

    Examples:
      | message       |
      | Pet not found |


	
	

	
	
	