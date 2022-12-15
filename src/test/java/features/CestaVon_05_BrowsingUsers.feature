@Marek
@BrowsingUsersTest
Feature: CestaVon - Browsing Users

  @Positive
  Scenario Outline: CestaVon - 05 - Browsing users

    Given   Open browser "<BROWSER>"
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "<TAB>"
    And     Click on Rola and choose option "<ROLA_OPTION>"
    And     Set Input Field "<SEARCH_FIELD>" to "<SEARCH_TEXT>"
    Then    Verify Meno "<SEARCH_TEXT>" and Rola "<ROLA_OPTION>" in filtered table

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | TAB          | ROLA_OPTION | SEARCH_FIELD | SEARCH_TEXT |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Používatelia | Omama       | Meno         |             |