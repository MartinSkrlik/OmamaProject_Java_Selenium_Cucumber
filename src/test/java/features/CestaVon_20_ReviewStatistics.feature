@Martin
@ReviewStatisticsTest
Feature: CestaVon - check properly ordered statistics


  Scenario Outline: CestaVon - 20 - check properly ordered statistics

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
#    And     Unwrap dropdown "Všetko"
#    Then    Select from menu tab "Omama"
#    And     Save users list
    And     Save users list in loop
#    And     Select from menu tab "Štatistiky"
#    And     Verify "Štatistiky" tab is active
#    And     Open "Omamy" statistics and verify details
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Edge  | Cestavon | admin    | Testcestavon1 | 1111    |



