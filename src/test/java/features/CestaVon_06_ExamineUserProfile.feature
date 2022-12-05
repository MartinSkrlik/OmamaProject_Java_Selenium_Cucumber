@Marek
@ExamineUserProfileTest
Feature: CestaVon - Examine User Profile

    @Positive
    Scenario Outline: CestaVon - Login with valid credentials and examine user profile

        Given   Open browser "<BROWSER>"
        When    Go to page "<PAGE>"
        And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
        And     Input pin code "<PINCODE>"
        And     Click on button "Zaregistrovať zariadenie"
        And     Select from menu tab "<TAB>"
        And     Select user with name "<USER_NAME>" and remember his information
        Then    Verify all information is visible

        Examples:

            | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | TAB          | USER_NAME |
            | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Používatelia | John Foxi |