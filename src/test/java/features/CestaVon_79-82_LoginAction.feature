@Martin
@LoginTest
Feature: CestaVon - Login Action

  @AdminSupervizorMentorTest
  Scenario Outline: CestaVon - admin - supervizor - mentor - positive

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME        | PASSWORD       | PINCODE |
      | Chrome  | Cestavon | admin           | Testcestavon1  | 1111    |
      | Chrome  | Cestavon | supervizor_test | Supervizor1234 | 1111    |
      | Chrome  | Cestavon | mentor_test     | Mentor1234     | 1111    |

  @Omamatest
  Scenario Outline: CestaVon - omama - positive

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Click on menu button
    And     Click on "Odhlásiť" button from menu
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME  | PASSWORD  | PINCODE |
      | Chrome  | Cestavon | omama_new | Omama1234 | 1111    |

  @LockOutedTest
  Scenario Outline: CestaVon - Login with lock-outed user - negative
    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify odhlasit button is not visible
    Then    Verify error message is visible

    Examples:

      | BROWSER | PAGE     | USERNAME        | PASSWORD        |
      | Chrome  | Cestavon | locked_out_user | locked_out_user |

