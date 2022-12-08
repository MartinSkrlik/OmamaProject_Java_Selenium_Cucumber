@CreateOmamaUserTest
Feature: CestaVon - Create New Omama User


  Scenario Outline: CestaVon - Login with valid credentials and create new Omama user

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    Then    Click on button "Pridať"
    And     Registry new "Omama" user and save details
    Then    Click on button "Registrovať"
    And     Verify "Používatelia" tab is active
    Then    Click on button "Obnoviť"
    And     Input into "Meno" search bar username "Martin Tester"
    And     Select user with name "Martin Tester"
    And     Verify details new created user
    Then    Click on button "Upraviť profil"
    And     Unwrap dropdown "Vyber Mentora"
    Then    Select from menu tab "Mentorka Testovacia"
    And     Unwrap dropdown "Začiatočníčka"
    Then    Select from menu tab "Expertka"
    Then    Select into input "Na úrovni od" actual date
    And     Unwrap dropdown "Vyber očakávaný počet detí"
    Then    Select from menu tab "3"
    And     Unwrap dropdown "Vyber typ úväzku"
    Then    Select from menu tab "Trojštvrtinový úväzok"
    Then    Click on button "Uložiť"
    Then    Click on button "Odstrániť používateľa"
    Then    Click on button "Odstrániť"
    Then    Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



