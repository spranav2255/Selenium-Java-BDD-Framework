Feature: To test the  Automation Excersize Demo Website

  Scenario: Verify the user have been navigated to the home page of the website
    Given The user is on the home page of the e-shopping application
    When user clicks on the products link
    Then the products page should be displayed
