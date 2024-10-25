Feature: Daraz Signup Functionality

  Scenario Outline: Successful signup with valid credentials on Daraz
    Given the user is on the Daraz signup page
    When the user enters valid details:
      | phoneNumber | smsCode | password  | fullName  | birthdayMonth | birthdayDay | birthdayYear | gender |
      | 03171292801 | 123456  | daraz123@ | John Doe  | January       | 1           | 1990         | Male   |
    And the user clicks on the signup button
    Then the user should be navigated to the Daraz home page

  Scenario Outline: Unsuccessful signup with invalid credentials on Daraz
    Given the user is on the Daraz signup page
    When the user enters invalid details:
      | phoneNumber   | smsCode | password  | fullName      | birthdayMonth | birthdayDay | birthdayYear | gender |
      | invalidPhone  | 123     | short     | Invalid User  | February      | 30          | 2010         | Female |
    And the user clicks on the signup button
    Then a signup error message should be displayed

  Scenario Outline: Unsuccessful signup with empty fields on Daraz
    Given the user is on the Daraz signup page
    When the user enters empty details
    And the user clicks on the signup button
    Then a signup error message should be displayed

  Scenario Outline: Unsuccessful signup with mismatched passwords on Daraz
    Given the user is on the Daraz signup page
    When the user enters details with mismatched passwords:
      | phoneNumber | smsCode | password  | fullName  | birthdayMonth | birthdayDay | birthdayYear | gender |
      | 03171292801 | 123456  | daraz123@ | John Doe  | January       | 1           | 1990         | Male   |
    And the user clicks on the signup button
    Then a signup error message should be displayed
