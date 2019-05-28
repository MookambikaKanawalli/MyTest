Feature: UI feature


  @ui @all
  Scenario Outline: UI_Test

    Given I navigate to the application
    Then I verify that I navigated to right page
    And I click on locate us button
    When I enter suburb details "<SUBURB>"
    Then I should see service centre named "<SERVICE_CENTRE_NAME>"


    Examples:
      | SUBURB                       | SERVICE_CENTRE_NAME         |
      | Sydney 2000                  | Marrickville Service Centre |
      | Sydney Domestic Airport 2020 | Rockdale Service Centre     |
