@LoginTest
Feature: Omama - Login Action

  @Positive
  Scenario Outline: Omama - Login with valid credentials - positive - Demo

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify page title "<TITLE>" is visible

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | TITLE    |
      | Chrome  | Cestavon | admin    | Testcestavon1 | Products |


  @Negative
  Scenario Outline: SAUCEDEMO - Login with lock-outed user - negative
    Given Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And    Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify page title is not visible
    And    Verify error message is visible

    Examples:

      | BROWSER | PAGE      | BROWSERVERSION | OS | OSVERSION | DEVICE | APPIUMVERSION | RESOLUTION | USERNAME        | PASSWORD     |
      | Chrome  | Saucedemo | -              | -  | -         | -      | -             | -          | locked_out_user | secret_sauce |
      	
