@EdidUserData
Feature: CestaVon - Edit user data


  Scenario Outline: CestaVon - Login with valid credentials,looking for specific user and edit data

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Find user "Martin Tester"
    And     Click on button "Upraviť profil"
    And     Change user details
    And     Set user status "neaktívny"
    And     Save user details
    Then    Click on button "Uložiť"
    And     Verify user details were changed
    And     Click on button "Upraviť profil"
    And     Change user details to original state
    And     Set user status "aktívny"
    And     Save user details
    Then    Click on button "Uložiť"
    And     Verify user details were changed
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |


