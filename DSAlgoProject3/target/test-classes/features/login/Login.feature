@LoginTest
Feature: Login Page validation
Scenario: Verify that user able to land on Home page after entering valid Username and Password fields
    Given User is on the login page
    When User enters valid credentials and clicks login button
    Then User should see the "Data Structures-Introduction" heading   
Scenario: Verify prompt to register is validated
    Given User is on the login page
    When User clicks the "Register" link under login button
    Then User should be redirected to registration page
Scenario: Verify  error message for all empty fields during Login    
    Given  User is on the login page
    When  User clicks login button after leaving the "Username" textbox and "Password" textbox empty
    Then User should see error message "Please fill out this field." below Username textbox 
Scenario: Verify error message for empty Password field during Login   
    Given User is on the login page
    When  User clicks login button after entering the "Username" and leaves "Password" textbox empty
    Then User should see error message "Please fill out this field." below Password textbox  
Scenario: Verify error message for empty Username field during Login
    Given User is on the login page
    When  User clicks login button after entering the Password only
    Then User should see error message "Please fill out this field." below Username textbox  
Scenario: Verify error message for invalid Username field during Login
    Given User is on the login page
    When  User clicks login button after entering invalid username and valid password
    Then  User should see an error message "Invalid Username and Password" 
Scenario: Verify Datastructures dropdown is visible 
    Given User is on the login page
    When  User clicks on dropdown in login page
    Then  Datastructures dropdown is visible  
Scenario: Verify RegisterLink is visible on the login page
    Given User is on the login page
    When  User navigates to Register link on loginpage
    Then  User should see Register link
Scenario: Verify Signin Link is visible 
    Given User is on the login page
    When  User navigates to Signin Link on loginpage
    Then  User should see Signin Link
Scenario: Verify Signin navigates to home page
    Given User is on the login page
    When  User enters valid credentials and clicks login button
    Then  User should be Logged in successfully  