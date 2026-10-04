# ButelkaKaucyjna
Plugin that adds deposit bottles

<details>
<summary><b>English</b></summary>

# What it adds
- **/informacje command that shows info about this plugin**
- **/nadaj \<player?\> \<amount?\> command that grants \<player\> \<amount\> of deposit bottles (perm: butelki.nadaj)**
- **/panel \<option\> command (perm: butelki.panel)**
  - **help**, **reload**, **check-vault**
- **Getting deposit bottles from specific ores**
- **Getting deposit bottles from killing mobs**
  - **Zombie, skeleton, creeper, spider, enderman**
- **Exchanging deposit bottles (Shift + Right Click) (items or money, depending on the server config)**
- **Config**
  - `allow-greeting-message`
  - **drop chances from specific ores**
  - **drop chances from specific mobs**
  - **deposit bottle config** (name, lore, enchant?, lore?)
  - **deposit bottle exchange config**
    - `use-vault-if-possible` (if u want you can disable economy)
    - `exchange-material` (item, that player gets, if economy is not set up)
    - `exchange-no-amount` (amount of `exchange-material`, if economy is not set up)
    - `exchange-yes-amount` (amount of money, if economy is not set up)
  - **configuration of every\* message**

# Languages
- **Polski** (pl_pl)
- **English** (en_us)

# Permissions
- **butelki.nadaj**
  - Allows granting bottles with /nadaj
  - By default, it's for **ops**
- **butelki.panel:**
  - **butelki.panel.help**
    - Allows checking plugin's help
    - By default, it's for **everyone**
  - **butelki.panel.reload**
    - Allows reloading the plugin
    - By default, it's for **ops**
  - **butelki.panel.check-vault**
    - Allows checking if deposit bottles work on economy
    - By default, it's for **ops**

*Everything that's up there was written for `sure 1.1.1` version*

*Every 0.X.X version does not guarantee successful build, if you do it yourself. If you want to test them out, use official releases in [Releases](../../releases)*

</details>

<details>
<summary><b>Polish</b></summary>

# Co dodaje
- **Komendę /informacje która pokazuje informacje o pluginie**
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

# Języki
- **Polski** (pl_pl)
- **English** (en_us)

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

*Wszystko co jest u góry było napisane dla wersji `sure 1.1.1`*

*Wszystkie wersje 0.X.X nie gwarantują poprawnego działania przy samodzielnej kompilacji. Jeśli chcesz je przetestować, używaj oficjalnych wydań w [Releases](../../releases)*

</details>