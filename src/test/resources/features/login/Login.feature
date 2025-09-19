@LoginTests
Feature: Login Page validation
@tag1
Scenario: Verify Signin Link is visible from Home Page
    Given User is on the home page
    When  User navigates to Signin Link 
    Then  User should see Signin Link
@tag2
Scenario: Verify that user able to land on Home page after entering valid Username and Password fields
    Given User is on the login page
    When User enters valid credentials and clicks login button
    Then User should see the "Data Structures-Introduction" heading   
