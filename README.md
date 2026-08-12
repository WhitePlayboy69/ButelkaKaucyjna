# ButelkaKaucyjna
Plugin który dodaje butelki kaucyjne

# Co dodaje
- **Komendę /informacje która pokazuje informacje o serwerze**
- **Komendę /nadaj \<gracz?\> \<ilość?\> która nadaje \<graczowi\> \<ilość\> butelek kaucyjnych (permisja: butelki.nadaj)**
- **Komendę /panel \<opcja\> (permisja: butelki.panel)**:
  - **help**, **reload**, **check-vault**
- **Wydobywanie butelek kaucyjnych z danych rud**
- **Dostawanie butelek kaucyjnych z zabijania mobów**
  - **Zombie, szkielet, creeper, pająk, enderman**
- **Wymienianie butelek kaucyjnych (Shift + PPM) (na itemy lub pieniądze w ekonomii w zależności od konfiguracji serwera)**
- **Config**
  - `allow-greeting-message`
  - **szanse dropów z rud**
  - **szanse dropów z mobów**
  - **konfiguracja butelki kaucyjnej** (nazwa, lore, enchant?, lore?)
  - **konfiguracja wymieniania butelki kaucyjnej**
    - `use-vault-if-possible` (możliwość wyłączenia ekonomii na życzenie)
    - `exchange-material` (item, który dostaje osoba po wymienieniu jeśli ekonomia nie jest skonfigurowana)
    - `exchange-no-amount` (ilość `exchange-material`, jeśli ekonomia nie jest skonfigurowana)
    - `exchange-yes-amount` (ilość dolarów po wymienieniu jeśli ekonomia jest skonfigurowana)
  - **konfiguracja wszystkich wiadomości**

# Permisje
- **butelki.nadaj**
  - Pozwala nadawać butelki komendą /nadaj
  - Domyślnie jest **dla operatorów**
- **butelki.panel:**
  - **butelki.panel.help**
    - Pozwala zobaczyć pomoc pluginu
    - Domyślnie jest **dla każdego**
  - **butelki.panel.reload**
    - Pozwala zreloadować config pluginu
    - Domyślnie jest **dla operatorów**
  - **butelki.panel.check-vault**
    - Pozwala sprawdzić czy butelki są na zasadach ekonomii
    - Domyślnie jest **dla operatorów**

*Wszystko co jest u góry było napisane dla wersji `sure 1.0`*

*Wszystkie wersje 0.X.X nie gwarantują poprawnego działania przy samodzielnej kompilacji. Jeśli chcesz je przetestować, używaj oficjalnych wydań w [Releases](../../releases)*
