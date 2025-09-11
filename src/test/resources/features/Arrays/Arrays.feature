@ArrayTest
Feature: Array page Functional Validation(after login)
Background:
    Given User is on the launch page
    When User clicks on "Get Started" button
    Then User should be navigated to the Home Page
    Given User clicks "Sign In" link on home page
    When User enters valid credentials
    And User clicks "Login" button
    Then User should be logged in successfully

Scenario: Verify that user is able to navigate to "Array" data structure page
    Given User on NumpyNinja homepage 
    When User selects "Array" item from the "Data Structures" drop down menu
    Then User should be directed to "ARRAY" Data Structure Page
