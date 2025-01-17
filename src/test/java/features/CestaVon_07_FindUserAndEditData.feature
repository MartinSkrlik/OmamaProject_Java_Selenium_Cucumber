@Martin
@EdidUserDataTest
Feature: CestaVon - Edit user data


  Scenario Outline: CestaVon - 07 - looking for specific user and edit data

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
    And     Input into "Meno" search bar username "<USER>"
    And     Select first user contains "<USER>"
    And     Click on button "Upraviť profil"
    And     Change user details
    And     Save user details
    Then    Click on button "Uložiť"
    And     Verify details
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | USER     |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | AutoTest |



