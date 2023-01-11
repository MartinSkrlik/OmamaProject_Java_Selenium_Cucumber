@Marek
@LessonFeedbackTest
Feature: CestaVon - Checking finished lesson feedback


  Scenario Outline: CestaVon - 19 - Checking finished lesson feedback

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Input into "Meno" search bar username "<FIRSTNAME>"
    And     Input into "Priezvisko" search bar username "<LASTNAME>"
    And     Select first row from table
    And     Choose first finished lesson
    And     Verify lesson details "<FIRSTNAME>" "<LASTNAME>"

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | FIRSTNAME | LASTNAME |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Jožko     | Mrkvička |



