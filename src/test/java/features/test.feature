Feature: Book flights
  Verify if user is able to search and book flights

  Scenario Outline: Book flights
    Given user is on NewTour homepage
    When user navigates to REGISTER Page
    Then user register successfully
    When user navigates to Flights
    Then user enters flight details from "London" to "Zurich" for date "<fromDay>" / "<fromMonth>" to "<toDay>" / "<toMonth>"
    And user search for the flight
    When user reserve flights
    And user buy flights
    Then success message is displayed
  Examples:
    | fromDay | fromMonth | toDay | toMonth |
    | 15 | 3 | 20 | 3 |
    | 24 | 5 | 31 | 5 |