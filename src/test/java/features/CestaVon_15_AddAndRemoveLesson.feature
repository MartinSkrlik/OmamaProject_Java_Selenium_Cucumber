@Marek
@AddAndRemoveLessonTest
Feature: CestaVon - Add and Remove Lesson for client


    Scenario Outline: CestaVon - 15 - Add and Remove Lesson for client

        Given   Open browser "<BROWSER>"
        When    Maximalize window
        When    Go to page "<PAGE>"
        And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
        And     Input pin code "<PINCODE>"
        And     Click on button "Zaregistrovať zariadenie"
        And     Select from menu tab "Klienti"
        And     Verify "Klienti" tab is active
        And     Select first row from table
        Then    Verify subpage "Lekcie" is active in clients profile
        Then    Remember number of rows
        And     Click on button "Pridať lekciu"
        And     Set date to "12/31/2023"
        And     Click on button "Potvrdiť"
        And     Verify number of rows is incremented
        And     Select first row from table
        And     Click on button "Vymazať  lekciu"
        And     Click on button "Áno"
        And     Verify number of rows is incremented
        And     Refresh current page
        And     Verify number of rows is the same

        Examples:

            | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
            | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



