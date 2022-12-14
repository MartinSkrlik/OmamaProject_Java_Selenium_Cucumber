@Martin
@SearchForUserTest
Feature: CestaVon - Search for user in klient tab


  Scenario Outline: CestaVon - looking for specific client in Klienti tab

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Input into "Meno" search bar username "<NAME>"
    And     Verify if in "Meno" search bar was filtered only username "<NAME>"
    And     Clear input "Meno"
    And     Input into "Priezvisko" search bar username "<SURNAME>"
    And     Verify if in "Priezvisko" search bar was filtered only username "<SURNAME>"
    And     Clear input "Priezvisko"
    And     Unwrap dropdown "Všetky omamy"
    And     Select from menu tab "<OMAMA>"
    And     Remember clients name and surname belong to omama "Alexandra Ginová"
    And     Select from menu tab "Používatelia"
    And     Verify "Používatelia" tab is active
    And     Input into "Meno" search bar username "<OMAMA>"// zmenit vysledok testcasu na pass, aj ked nenajde user
    And     Select user with name "<OMAMA>"
    And     Verify clients username belong to "<OMAMA>"
    And     Select from menu tab "Odhlásiť"
    Then    Verify Login page is present

    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | NAME         | SURNAME      | OMAMA            |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Anna         | Vrbova       | Alexandra Ginová |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | DoesNotExist | DoesNotExist | Alexandra Ginová |


