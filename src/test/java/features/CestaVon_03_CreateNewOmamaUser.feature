@Martin
@CreateOmamaUserTest
Feature: CestaVon - Create New Omama User


  Scenario Outline: CestaVon - create and delete New Omama user

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    Then    Click on button "Pridať"
    And     Registry new "<USER>" and save details
    Then    Click on button "Registrovať"
    And     Verify "Používatelia" tab is active
    Then    Click on button "Obnoviť"
    And     Find user with changed details
    And     Select user with changed details
    And     Verify omama details
    Then    Click on button "Upraviť profil"
    And     Unwrap dropdown "Vyber Mentora"
    Then    Select from menu tab "Mentorka Testovacia"
    And     Unwrap dropdown "Začiatočníčka"
    Then    Select from menu tab "Juniorka"
    Then    Select into input "Na úrovni od" actual date
    And     Unwrap dropdown "Vyber očakávaný počet detí"
    Then    Select from menu tab "4"
    And     Unwrap dropdown "Vyber typ úväzku"
    Then    Select from menu tab "Trojštvrtinový úväzok"
    Then    Save omama specification
    Then    Click on button "Uložiť"
    Then    Wait for changes is processed
    Then    Click on button "Upraviť profil"
    And     Verify omama specification was changed
    Then    Click on button "Uložiť"
    And     Click on button "Odstrániť používateľa"
    And     Click on button "Odstrániť"
#    And     Select from menu tab "Odhlásiť"
#    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | USER  |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Omama |



