@Martin
@BrowseInProfil
Feature: CestaVon - Browsing in profil


  Scenario Outline: CestaVon - 14 - Browsing in profil

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Input into "Meno" search bar username "<NAME>"
    And     Input into "Priezvisko" search bar username "<SURNAME>"
    And     Select user with name "<NAME>"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | NAME | SURNAME |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Anna | Modrá   |



