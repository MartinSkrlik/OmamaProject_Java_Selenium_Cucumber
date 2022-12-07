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
    And     Input into "Meno" search bar username "Neexistujuce meno"
    And     Verify if in "Meno" search bar was filtered only username "Neexistujuce meno"
    And     Clear input "Meno"
    And     Input into "Priezvisko" search bar username "Mrkvička"
    And     Verify if in "Priezvisko" search bar was filtered only username "Mrkvička"
    And     Clear input "Priezvisko"
    And     Unwrap dropdown "Všetky omamy"
    And     Select from menu tab "Alexandra Ginová"
    And     Remember clients name and surname belong to omama "Alexandra Ginová"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
    And     Input into "Meno" search bar username "Alexandra Ginová"
    And     Select user with name "Alexandra Ginová"
    And     Verify clients username belong to "Alexandra Ginová"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    |



