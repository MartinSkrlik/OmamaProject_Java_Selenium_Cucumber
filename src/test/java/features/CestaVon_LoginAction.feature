@LoginTest
Feature: CestaVon - Login Action

  @Admin
  Scenario Outline: CestaVon - admin - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |

  @Supervisor
  Scenario Outline: CestaVon - supervisor - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME        | PASSWORD       | PINCODE |
      | Chrome  | Cestavon | supervizor_test | Supervizor1234 | 1111    |

  @Mentor
  Scenario Outline: CestaVon - mentor - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME    | PASSWORD   | PINCODE |
      | Chrome  | Cestavon | mentor_test | Mentor1234 | 1111    |

  @Omama
  Scenario Outline: CestaVon - omama - positive

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Click on menu button
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME   | PASSWORD  | PINCODE |
      | Chrome  | Cestavon | omama_test | Omama1234 | 1111    |

  @Negative
  Scenario Outline: CestaVon - Login with lock-outed user - negative
    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    Then    Verify odhlasit button is not visible
    Then    Verify error message is visible

    Examples:

      | BROWSER | PAGE     | USERNAME        | PASSWORD        |
      | Chrome  | Cestavon | locked_out_user | locked_out_user |

