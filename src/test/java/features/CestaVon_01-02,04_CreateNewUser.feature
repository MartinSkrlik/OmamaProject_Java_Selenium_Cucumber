@CreateUserTest
Feature: CestaVon - Create new user


  Scenario Outline: CestaVon - create new admin, mentor and supervisor user and deleted them.

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Click on button "Pridať"
    And     Registry new "<USER>" user and save details
    And     Click on button "Registrovať"
    And     Verify "Používatelia" tab is active
    And     Click on button "Obnoviť"
    And     Input into "Meno" search bar username "Martin Tester"
    And     Select user with name "Martin Tester"
    Then    Verify details new created user
    And     Click on button "Odstrániť používateľa"
    And     Click on button "Odstrániť"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | USER       |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Admin      |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Mentor     |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Supervízor |

