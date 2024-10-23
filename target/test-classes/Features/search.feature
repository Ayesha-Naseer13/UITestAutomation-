Feature: Product Search on Daraz

  Scenario: Searching for products using keywords from an Excel file
    Given user is on the Daraz homepage
    When user searches for products from Excel sheet "Sheet1"
    Then search results are displayed for the keywords from Excel
