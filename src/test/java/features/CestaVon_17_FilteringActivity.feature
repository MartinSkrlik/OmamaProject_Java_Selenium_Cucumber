@Martin
@FilteringNewActivityTest
Feature: CestaVon - filter activity with activity attribute


  Scenario Outline: CestaVon - 17 - filter activity with activity attribute

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Aktivity"
    And     Verify "Aktivity" tab is active
    And     Input into "Názov aktivity" search bar username "<ACTIVITY_NAME>"
    And     Save activity attributes
    And     Clear input "Názov aktivity"
    And     Verify if name attribute filter desired activity
    And     Verify if number attribute filter desired activity
    And     Verify if month attribute filter desired activity
    And     Verify if week attribute filter desired activity
    And     Verify if tab Nazov properly sorting data
    And     Verify if tabs Číslo lekcie, Mesiac and Týždeň properly sorting data
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | ACTIVITY_NAME   |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | AutoTest Martin |



