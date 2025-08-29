@DatastructuresNavigationTest 
Feature: DataStructures Navigation Page Validation

  Background:
    Given User is on "Time Complexity" page for timecomplexity
    When User clicks on Data Structures dropdown
@DropDownlist
  Scenario Outline: Verify navigation to <module> module
    When User selects "<module>" from the Data Structures dropdown
    Then User should be navigated to the "<expectedPath>" module page

    Examples:
      | module       | expectedPath   |
      | Arrays       | arrays         |
      

