@SearchForUser
Feature: CestaVon - Search for user in klient tab


  Scenario Outline: CestaVon - Login with valid credentials,looking for specific client in Klienti tab

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Input into "Meno" search bar username "Ivan"
    And     Verify if in "Meno" search bar was filtered only username "Ivan"
    And     Clear input "Meno"
    And     Input into "Priezvisko" search bar username "Mrkvička"
    And     Verify if in "Priezvisko" search bar was filtered only username "Mrkvička"
    And     Clear input "Priezvisko"
    And     Unwrap dropdown "Všetky omamy"
    And     Select from menu tab "Alexandra Ginová"
    And     Verify if filtered clients belong "Alexandra Ginová"



              #    And     Find user "Martin Tester"
#    And     Click on button "Odstrániť používateľa"
#    And     Click on button "Odstrániť"
#    And     Verify "Používatelia" tab is active
#    And     Click on button "Obnoviť"
#    And     Input into "Meno" search bar username "Martin Tester"
  #    And     Select from menu tab "Odhlásiť"
#    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



