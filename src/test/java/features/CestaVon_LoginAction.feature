@LoginTestAdmin
Feature: CestaVon - Login Action

  @Positive
  Scenario Outline: CestaVon - Login with valid credentials - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify odhlasit button is visible

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      |
      | Chrome  | Cestavon | admin    | Testcestavon1 |

  @Negative
  Scenario Outline: CestaVon - Login with lock-outed user - negative
    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify odhlasit button is not visible
    And     Verify error message is visible

    Examples:

      | BROWSER | PAGE     | USERNAME        | PASSWORD        |
      | Chrome  | Cestavon | locked_out_user | locked_out_user |
      	
