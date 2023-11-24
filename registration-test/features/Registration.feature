#Author: Bhanu
Feature: End-to-end test scenarios for Registration and Dashboard page functionality

  Background: 
    Given user opens the browser
    When user navigates to the Home page
    Then check Home page is displayed with introductory text

  @functional @positive
  Scenario Outline: Verify that after successful registration, user sees the Dashboard Page with a personalised greeting and a list of 5 jobs
    When user clicks on 'Register' link
    Then check Registration page is displayed
    When user enter Registration form details as:
      | <Name> | <Email> | <Web_address> | <Interests> | <Password> | <Confirm_password> |
    And user clicks on Register button
    Then check Dashboard page is displayed
    Then verify Dashboard shows success alert "Your registration has been successful!" and welcome message <welcome_message>
    Then verify Dashboard table shows a list of 5 jobs

    Examples: 
      | Name     | Email              | Web_address      | Interests       | Password     | Confirm_password | welcome_message     |
      |          | john@gmail.com     |                  | Test Engineer   | Pa55W0rd     | Pa55W0rd         | "Welcome!"          |
      | John     | john_s@gmail.com   | http://john-url  | Test Engineer   | Pa55W0rd     | Pa55W0rd         | "Welcome John!"     |
      | John$5   | John$5@xyz.co.uk   | https://john-url | Testing,QA,Java | Pa55W0rd$£!  | Pa55W0rd$£!      | "Welcome John$5!"   |
      | John Lee | John.Lee@gmail.com | ftp://john-url   | Developer,C++   | Pa55 W0rd$ ! | Pa55 W0rd$ !     | "Welcome John Lee!" |
      |          | john@gmail.com     | www.john-url.com | Test Engineer   | Pa55W0rd     | Pa55W0rd         | "Welcome!"          |

  @functional @negative
  Scenario: Verify that on entering invalid email address, validation error message is displayed to the user
    When user clicks on 'Register' link
    Then check Registration page is displayed
    When user enters "" in Email field
    And user clicks on Register button
    Then check email validation error message is displayed
    When user enters "admingmail.com" in Email field
    And user clicks on Register button
    Then check email validation error message is displayed
    When user enters "admin@gmail.com" in Email field
    And user clicks on Register button
    Then check email validation error message is not present
    When user enters "admin@gmailcom" in Email field
    And user clicks on Register button
    Then check email validation error message is displayed
    When user enters "$$$@gmail.com" in Email field
    And user clicks on Register button
    Then check email validation error message is displayed
    When user enters "abc1@gmail.com" in Email field
    And user clicks on Register button
    Then check email validation error message is not present
    
  @functional @negative
  Scenario: Verify that if Password and Confirm password are not the same or not atleast 8 characters, validation error message is displayed
    When user clicks on 'Register' link
    Then check Registration page is displayed
    When user enters "" in Password field
    And user enters "" in Confirm password field
    And user clicks on Register button
    Then check password length validation error message is displayed
    When user enters "1234567" in Password field
    And user enters "1234567" in Confirm password field
    And user clicks on Register button
    Then check password length validation error message is displayed
    When user enters "12345678" in Password field
    And user enters "12345678" in Confirm password field
    And user clicks on Register button
    Then check password length validation error message is not present
    When user enters "12345678" in Password field
    And user enters "1234567A" in Confirm password field
    And user clicks on Register button
    Then check passwords match validation error message is displayed
    When user enters "12345678" in Password field
    And user enters "12345678" in Confirm password field
    And user clicks on Register button
    Then check passwords match validation error message is not present
    