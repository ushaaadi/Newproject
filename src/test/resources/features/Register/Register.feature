@RegisterTest
Feature: Register page Validation
Scenario: Verify User clicks on Register link to access registration page
    Given User is on DS algo home page
    When User clicks on the Register link in the Home Page
    Then User is directed to the registration page  where they can fill out the signup form

Scenario: Verify User successfully registers with letters as username and other valid fields
    Given User on Registration page
    When User clicks Register button after entering Username with letters
    Then User should see a success message on the page  
Scenario: Verify User successfully registers with username containing digits and other valid fields
    Given User on Registration page
    When User clicks Register button after entering Username with digits
    Then User should see a success message on the page 
Scenario: Verify User successfully registers with @/./+/-/_ as username and other valid fields
    Given User on Registration page
    When The user enters the following details: and clicks the Register button
    Then User should see a success message on the page 
Scenario: Verify Username with space and other valid input fields
    Given User on Registration page
    When User enters Username with space, valid password, password confirmation and clicks Register
    Then User should see error message 
Scenario: Verify that user receives error message for all empty fields during registration
    Given User on Registration page
    When User clicks Register with all fields empty
    Then User should remain on the registration page 
Scenario: Verify that user receives error message for valid Username and all empty fields during registration
    Given User on Registration page
    When User enters valid Username with empty Password,empty Password Confirmation fields and clicks on Register button
    Then User should remain on the registration page
Scenario: Verify that user receives error message for empty Username and all valid fields during registration
    Given User on Registration page
    When User enters empty Username with all valid fields and clicks on Register button
    Then User should remain on the registration page 
Scenario: Verify that user receives error message for valid Username,valid Password and empty Password confirmation fields during registration
    Given User on Registration page
    When User enters valid Username, valid Password, empty Password Confirmation fields and clicks on Register button
    Then User should remain on the registration page 
Scenario: Verify that user receives error message for existing Username,Password and Password confirmation fields during registration
    Given User on Registration page
    When User enters existing Username, Password, Password Confirmation fields and clicks on Register button
    Then User should see error message                                       
Scenario: Verify user password can’t be too similar to other personal information
    Given User on Registration page
    When User enters Username, sets Password, Passwordconfirmation as similar to user information and clicks on Register button
    Then User should see error message 
Scenario: Verify user enters Password exactly 8 characters
    Given User on Registration page
    When User enters valid username,Password ,Password Confirmation of exact characters and clicks on Register button
    Then User should be able to register 
Scenario: Verify user enters Password  lessthan characters
    Given User on Registration page
    When User enters valid username,Password ,Password Confirmation of characters and clicks on Register button
    Then User should see error message  
Scenario: Verify user enters Password 6 characters and whitespaces
    Given User on Registration page
    When User enters valid username,Password, Password Confirmation of characters,whitespaces and clicks on Register button
    Then User should see error message         
Scenario: Verify System rejects commonly used passwords
    Given User on Registration page
    When User enters valid username,password, confirmation password  and clicks on register button
    Then User should see error message 
Scenario: Verify System rejects password that is entirely numeric
    Given User on Registration page
    When User enters valid username, password with only numbers, confirms the same password and clicks on register button
    Then User should see error message 
Scenario: Verify that user receives error message for mismatched Password and Password Confirmation field during registration
    Given User on Registration page
    When  User clicks Register button after entering different passwords in Password and Password Confirmation fields 
     Then User should see error message  
Scenario: Verify NumpyNinja is visible 
    Given User on Registration page
    When User navigates to NumpyNinja on registrationpage
    Then User should see NumpyNinja title 
Scenario: Verify NumpyNinja navigates to corresponding page 
    Given User on Registration page
    When User clicks on NumpyNinja link on registrationpage
    Then User redirects to DSalgo portal 
Scenario: Verify RegisterLink is visible on the register page
    Given User on Registration page
    When User navigates to Register link on register page
    Then User should see Register link on register page     
Scenario: Verify RegisterLink navigates to Register page
    Given User on Registration page
    When User clicks on Registerlink on registerpage
    Then User redirects to Registration page       
Scenario: Verify Signin Link is visible 
    Given User on Registration page
    When User navigates to Signin Link on loginpage from registerpage
    Then User should see Signin Link on register page   

                            