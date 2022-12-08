@CreateMentorUser
Feature: CestaVon - Create New Mentor User


  Scenario Outline: CestaVon - Login with valid credentials and create new mentor user

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Click on button "Pridať"
    And     Registry new "Mentor" user and save details
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

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



