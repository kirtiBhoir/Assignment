Feature: search flight api testing

  #Scenario to verify search api using rest assured for city name provided
  @API
  Scenario Outline: Validate city airport details through api
    When user wants details for "<cityName>"
    Then user validates status details
    Then user validates headers
    Then user validates city details for "<cityName>"

    Examples:
    | cityName|
    | mumbai |
  
  
   
   