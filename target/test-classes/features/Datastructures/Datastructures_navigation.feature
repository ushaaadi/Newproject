Feature: DataStructures Navigation Page Validation

Background:
  Given User is on the login page
  When User logs in with valid credentials
  And User clicks on Data Structures dropdown

@DropDownlist
Scenario Outline: Verify navigation to <module> module
  Given User is on the login page
  When User logs in with valid credentials
  And User clicks on Data Structures dropdown
  And User selects "<module>" from the Data Structures dropdown
  Then User should be navigated to the "<expectedPath>" module page

Examples:
  | module       | expectedPath   |
  | Arrays       | arrays         |
  | Linked List  | linked-list    |
  | Stack        | stack          |
  | Queue        | queue          |
  | Tree         | tree           |
  | Graph        | graph          |
  