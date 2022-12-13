@EdidUserStatusTest
Feature: CestaVon - Edit user status


  Scenario Outline: CestaVon - looking for specific user and edit user status

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
    And     Find user "neaktívna"
    And     Click on button "Upraviť profil"
    And     Save user details
    And     Set user status "aktívny"
    Then    Click on button "Uložiť"
    Then    Verify details new created user
    Then    Go back to previous page
    And     Verify "Používatelia" tab is active
    And     Click on button "Obnoviť"
    And     Find user with changed details
    And     Select user with changed details
    Then    Verify details new created user
    And     Click on button "Upraviť profil"
    And     Set user status "neaktívny"
    And     Save user details
    Then    Click on button "Uložiť"
    Then    Verify details new created user
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



