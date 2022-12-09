@CreateNewClientTest
Feature: CestaVon - Create new client add omama


  Scenario Outline: CestaVon - Login with valid credentials, create new client and add omama to this client.

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Click on button "Pridať"
    And     Create new client and fill application form
    And     Select from Ano-Nie picker "Predškolský klub" choice "Áno"
    And     Unwrap dropdown "Vyber omamu"
    Then    Select from menu tab "Alexandra Ginová"
    And     Fill information about Rodina
#    And     Fill information about Tehotenstvo a pôrod
#    And     Fill information about Kojenie
#    And     Fill information about Psychomotorický vývin dieťaťa
#    And     Fill information about Všeobecné zdravie
#    And     Fill information about Obavy
#    And     Fill information about Bývanie
#    And     Fill information about škola
#    And     Fill information about Ochrana osobných údajov
#
#    And     Select from menu tab "Odhlásiť"
#    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



