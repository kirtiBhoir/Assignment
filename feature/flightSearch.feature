Feature: search flight

  #Scenario to verify search functionality for round trip
  Scenario: Search flights for round trip
    Given After navigating to cleartrip website user should see cleartrip homepage
    When user select trip type as "tripType"
    When user enters city as "fromCity" and "toCity"
    When user wants to "depart" on "departDate"
    When user wants to "return" on "returnDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    When user searches for the flights
    Then user should see flight search results

  #Scenario to verify search functionality without cities provided
  Scenario: Search flights without any cities provided
    Given After navigating to cleartrip website user should see cleartrip homepage
    When user searches without any values
    Then user should see error message

  #Scenario to verify search functionality for one-way trip
  Scenario: Search flights for one way trip
    Given After navigating to cleartrip website user should see cleartrip homepage
    When user select trip type as "tripType"
    When user enters city as "fromCity" and "toCity"
    When user wants to "depart" on "departDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    When user searches for the flights
    Then user should see flight search results

  #Scenario to verify search api using rest assured for city name provided
  Scenario: Validate city airport details through api
    Given user wants details for "cityName"
    Then user validates status details
    And user validates headers
    Then user validates city details for "cityName"
