@Martin
@AlterAndDeleteActivityTest
Feature: CestaVon - find activity, alter data and delete


  Scenario Outline: CestaVon - 18 - find activity, alter data and delete

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Aktivity"
    And     Verify "Aktivity" tab is active
    Then    Click on button "Pridať"
    And     Fill information about Activity
    And     Save activity details
    And     Click on button "Pridaj novú aktivitu"
    And     Click ESC button
    And     Select from menu tab "Aktivity"
    And     Verify "Aktivity" tab is active
    And     Click on button "Obnoviť"
    And     Find new created activity
    And     Click on button "Upraviť"
    And     Change activity details
    And     Save activity details
    And     Click on button "Uprav aktivitu"
    And     Select from menu tab "Klienti"
    And     Select from menu tab "Aktivity"
    And     Verify "Aktivity" tab is active
    And     Click on button "Obnoviť"
    And     Find new created activity
    And     Verify activity details
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



