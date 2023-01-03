@Marek
@EditClientInfoTest
Feature: CestaVon - Edit client info


  Scenario Outline: CestaVon - 13 - Edit Client Info and Activate/Deactivate account

    Given   Open browser "<BROWSER>"
    When    Maximalize window
    When    Go to page "<PAGE>"
    And     Login user with username SECURE "<USERNAME>" and password SECURE "<PASSWORD>"
    And     Input pin code "<PINCODE>"
    And     Click on button "Zaregistrovať zariadenie"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Input into "Meno" search bar username "<FIRSTNAME>"
    And     Input into "Priezvisko" search bar username "<LASTNAME>"
    And     Select first row from table
    And     Click on button "Upraviť profil"
    And     Save First and Last Name and Omama
    And     Set Input Field "Meno" to "<NEW_FIRSTNAME>"
    And     Set Input Field "Priezvisko" to "<NEW_LASTNAME>"
    And     Set Omama to "<NEW_OMAMA>"
    And     Save client profile edit
    Then    Verify client name is "<NEW_FIRSTNAME>" and "<NEW_LASTNAME>"
    Then    Verify Omama is "<NEW_OMAMA>"
    And     Click on button "Upraviť profil"
    And     Set Input Field "Meno" to "<FIRSTNAME>"
    And     Set Input Field "Priezvisko" to "<LASTNAME>"
    And     Set Omama to old Omama
    And     Save client profile edit
    Then    Verify client name is "<FIRSTNAME>" and "<LASTNAME>"
    Then    Verify Omama is old Omama
    And     Click on button "Deaktivovať profil"
    And     Fill calendar with today date
    And     Click on button "Deaktivovať"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Refresh current page
    And     Input into "Meno" search bar username "<FIRSTNAME>"
    And     Input into "Priezvisko" search bar username "<LASTNAME>"
    Then    Verify first client active status is "<STATUS_1>"
    Then    Select first row from table
    And     Click on button "Aktivovať profil"
    And     Click on button "Aktivovať"
    And     Select from menu tab "Klienti"
    And     Verify "Klienti" tab is active
    And     Refresh current page
    And     Input into "Meno" search bar username "<FIRSTNAME>"
    And     Input into "Priezvisko" search bar username "<LASTNAME>"
    Then    Verify first client active status is "<STATUS_2>"


    Examples:

      | BROWSER | PAGE     | USERNAME | PASSWORD      | PINCODE | FIRSTNAME | LASTNAME  | NEW_FIRSTNAME | NEW_LASTNAME   | NEW_OMAMA   | STATUS_1  | STATUS_2 |
      | Chrome  | Cestavon | admin    | Testcestavon1 | 1111    | Alica     | Babiaková | Testmeno      | Testpriezvisko | Omama Piata | neaktívny | aktívny  |



