Feature: search flight

  Background: 
    Given After navigating to cleartrip website user should see cleartrip homepage

  #Scenario to verify search functionality for round trip
  @UI
  Scenario: Search flights for round trip
    When user select trip type as "tripType"
    And user enters city as "fromCity" and "toCity"
    And user wants to depart on "departDate"
    And user wants to return on "returnDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    And user searches for the flights
    Then user should see flight search results

  #Scenario to verify search functionality without cities provided
  @UI
  Scenario: Search flights without any cities provided
    When user searches without any values
    Then user should see error message

  #Scenario to verify search functionality for one-way trip
  @UI
  Scenario: Search flights for one way trip
    When user select trip type as "tripType"
    And user enters city as "fromCity" and "toCity"
    And user wants to depart on "departDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    And user searches for the flights
    Then user should see flight search results
