Feature: Validating Store API's

  Scenario Outline: Verify that order is placed correctly using petStoreAPI

    Given user places new order with "<orderId>" "<id>" "<quantity>"
    When user calls "storeAPI" with "POST" https request
    Then the API call got success with status code 200
    And "id" in response body is "<orderId>"
    And "quantity" in response body is "<quantity>"

    Examples:
      | id   | orderId | quantity |
      | 3214 | 9       | 1        |


  Scenario Outline: Verify that order is deleted successfully using petStoreAPI
    Given there is an order with "<orderId>"
    When user calls "storeAPI" with "Delete" https request
    Then the API call got success with status code 200
    When user calls "storeAPI" with "GET" https request
    Then "message" in response body is "<message>"

    Examples:
      | orderId | message         |
      | 9       | Order not found |











