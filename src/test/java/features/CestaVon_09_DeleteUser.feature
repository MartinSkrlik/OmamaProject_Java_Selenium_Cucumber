@DeleteUserTest
Feature: CestaVon - Delete User


  Scenario Outline: CestaVon - Login with valid credentials,looking for specific user and delete user

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
    And     Find user "Martin Tester"
    And     Click on button "Odstrániť používateľa"
    And     Click on button "Odstrániť"
    And     Verify "Používatelia" tab is active
    And     Click on button "Obnoviť"
    And     Input into "Meno" search bar username "Martin Tester"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



