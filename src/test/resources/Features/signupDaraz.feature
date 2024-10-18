Feature: Daraz signup functionality

  Scenario Outline: Successful signup with valid credentials on Daraz
    Given user is on the Daraz signup page
    When user enters valid details
      | phoneNumber   | smsCode | password  | fullName      | birthdayMonth | birthdayDay | birthdayYear | gender |
      | 03171292801   | 123456  | daraz123@ | John Doe      | January       | 1           | 1990         | Male   |
    And user clicks on the Daraz signup button
    Then user is navigated to the Daraz home page

  Scenario Outline: Unsuccessful signup with invalid credentials on Daraz
    Given user is on the Daraz signup page
    When user enters invalid details
      | phoneNumber   | smsCode | password  | fullName      | birthdayMonth | birthdayDay | birthdayYear | gender |
      | invalidPhone  | 123     | short     | Invalid User  | February      | 30          | 2010         | Female |
    And user clicks on the Daraz signup button
    Then sign up error message is displayed

  Scenario Outline: Unsuccessful signup with empty fields on Daraz
    Given user is on the Daraz signup page
    When user enters empty details
    And user clicks on the Daraz signup button
    Then sign up error message is displayed

  Scenario Outline: Unsuccessful signup with mismatched passwords on Daraz
    Given user is on the Daraz signup page
    When user enters details with mismatched passwords
      | phoneNumber   | smsCode | password  | fullName      | birthdayMonth | birthdayDay | birthdayYear | gender |
      | 03171292801   | 123456  | daraz123@ | John Doe      | January       | 1           | 1990         | Male   |
    And user clicks on the Daraz signup button
    Then sign up error message is displayed
