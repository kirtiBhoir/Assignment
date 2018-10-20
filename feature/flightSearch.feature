Feature: search flight
  User tries to search flights without any values

  Scenario: Search flights for one way trip
    Given After navigating to cleartrip website user should see cleartrip homepage
    When user select trip type as "one way"
    When user enters city as "fromCity" and "toCity"
    When user wants to "depart" on "departDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    When user searches for the flights
    Then user should see flight search results
    
    
  Scenario: Search flights without any cities provided
    Given After navigating to cleartrip website user should see cleartrip homepage
  	When user searches without any values
   	Then user should see error message

  Scenario: Search flights for round trip
    Given After navigating to cleartrip website user should see cleartrip homepage
    When user select trip type as "round trip"
    When user enters city as "fromCity" and "toCity"
    When user wants to "depart" on "departDate"
    When user wants to "return" on "returnDate"
    And user enters "adultCount" along with "childrenCount" and "infantCount"
    When user searches for the flights
    Then user should see flight search results