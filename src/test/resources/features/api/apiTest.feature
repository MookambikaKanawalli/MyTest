Feature: API feature


  @api @all
  Scenario Outline: API_Test_current

    Given I make a GET request with "<Latitutde>" and "<Longitute>"
    Then I verify that state code

    Examples:
      | Latitutde | Longitute  |
      | 40.730610 | -73.935242 |


  @api @all
  Scenario Outline: API_Test_Foreast
    #The 5 day hourly forecast is not available on the free plan. Please see our pricing.

    Given I make a GET request with "<POST_CODE>"
    Then I verify forecast response

    Examples:
      | POST_CODE |
      | 2145      |