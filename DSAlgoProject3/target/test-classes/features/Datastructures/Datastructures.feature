@DatastructuresTest 
Feature: DataStructures Page Validation

Background:

    Given User is on the Home page after logging in
    When User clicks the Getting Started button in Data Structures - Introduction
   
Scenario: Check "Data structures-Introduction" Content display on DS page
    Given User is on the "Data Structures" page
    When User views "Data Structures-Introduction" on DS page
   Then User should see displayed content under Datastructures module  
   
Scenario: Check "Topics Covered" Content display on DS page
    Given User is on the "Data Structures" page
    When User views "Topics Covered" on DS page
   Then User should see displayed content under Datastructures module
   
Scenario: Verify Time Complexity page is accessible from Introduction
    Given User on "Data Structures-Introduction" page
    When User clicks on "Time Complexity" link
   Then User should see "Time Complexity" page  
   
Scenario:  Verify content on Time Complexity page is displayed correctly
    Given User is on "Time Complexity" page for timecomplexity
    When User navigates to view "Time Complexity" page
   Then User should see content in "Time Complexity" page
   
Scenario:  Verify that user is able to navigate to "try Editor" page
   Given User is on "Time Complexity" page for timecomplexity
    When User clicks "Try Here" button
   Then User should be redirected to a page having an try Editor with a Run button to test
   
Scenario: Verify that user receives error when click on Run button without entering code
   Given  User is on the "tryEditor" page for timecomplexity
    When User clicks the "Run" without entering the code in the Editor
  
Scenario:  Verify that user receives error for invalid python code
    Given  User is on the "tryEditor" page for timecomplexity
    When  User writes the invalid code in Editor and clicks the "Run" Button
   Then User should be able to see an error message in alert window  
   
Scenario:  Verify that user is able to see output for valid python code
    Given  User is on the "tryEditor" page for timecomplexity
    When  User writes the valid code in Editor and clicks the "Run" Button
   Then  User should be able to see output in the console   

Scenario: Verify Navigate back from editor to topic page
    Given  User is on the "tryEditor" page for timecomplexity
    When  User clicks on back arrow
   Then  User should see displayed content under Datastructures module 
   
Scenario: Verify Output area clears on new run
    Given  User is on the "tryEditor" page for timecomplexity
    When  User "Run" the code, modifies it, and runs it again
   Then  User should see old output replaced with new output     
           
Scenario: Verify behavior when user clicks back button after writing code on Time Complexity Run page
    Given User is on the "tryEditor" page for timecomplexity
    When  User writes the valid code in Editor and clicks the back arrow
   Then  User should see displayed content under Datastructures module 
   

   
   
           
       

  