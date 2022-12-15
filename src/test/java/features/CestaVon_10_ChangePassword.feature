@Marek
@ChangePasswordTest
Feature: CestaVon - Change Password

  @Positive
  Scenario Outline: CestaVon - 10 - Change password - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "<TAB>"
    And     Click on Change password
    And     Set old password as "<PASSWORD>"
    And     Set new password as "<NEW_PASSWORD>"
    And     Set confirm new password as "<NEW_PASSWORD>"
    And     Confirm password change
    And     Click on Change password
    And     Set old password as "<NEW_PASSWORD>"
    And     Set new password as "<PASSWORD>"
    And     Set confirm new password as "<PASSWORD>"
    And     Confirm password change

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | TAB        | NEW_PASSWORD  |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Nastavenia | Testcestavon2 |

  @Negative
  Scenario Outline: CestaVon - 10 - Change password - negative

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "<TAB>"
    And     Click on Change password
    And     Set old password as "<PASSWORD>"
    And     Set new password as "<NEW_PASSWORD>"
    And     Set confirm new password as "<NEW_PASSWORD2>"
    And     Confirm password change negative

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | TAB        | NEW_PASSWORD  | NEW_PASSWORD2 |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Nastavenia | testcestavon2 | testcestavon2 |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Nastavenia | testcestavon2 | Testcestavon2 |