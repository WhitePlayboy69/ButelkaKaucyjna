package xyz.playboy

object FromConfig {
    var allow_greeting_message = true
    var kaucyjna_with_infinity = true
    var disable_kaucyjna_lore = false

    var kaucyjna_name = "&6Butelka kaucyjna"
    var kaucyjna_lore = "&fButelka, którą można wymienić w butelkomacie."

    var coal_ore_chance = 20
    var copper_ore_chance = 15
    var iron_ore_chance = 30
    var gold_ore_chance = 30
    var emerald_ore_chance = 60
    var redstone_ore_chance = 20
    var lapis_ore_chance = 15
    var diamond_ore_chance = 50
    var ancient_debris_chance = 60

    var zombie_chance = 30
    var skeleton_chance = 30
    var creeper_chance = 40
    var spider_chance = 25
    var enderman_chance = 60

    var exchange_material = "gold_nugget"
    var exchange_no_amount = 1
    var exchange_yes_amount = 0.5
}

object Messages {
    var unsufficient_permission = "§cNie masz wystarczających permisji by to zrobić!"
    var successfully_config_reloaded = "&aPomyślnie zreloadowano config!"
    var player_is_offline = "&cTen gracz jest offline!"
    var kaucja_successfully_given = "&aPomyślnie nadano butelkę kaucyjną!"
    var wrong_args_nadaj = "&cPoprawne użycie: &f/nadaj <gracz> <ilość>&c!"
    var greeting_first = "&aWitaj na serwerze!"
    var greeting_second = "&aZbieraj &bbutelki kaucyjne &akopiąc i zabijając!"
    var kaucja_from_this_block = "&aZdobyłeś kaucję z tego bloku!"
    var kaucja_from_this_mob = "&aZdobyłeś kaucję z tego moba!"
    var could_not_exchange = "&cNie można było wymienić butelek kaucyjnych! Skontaktuj się z administratorem."

    var kaucja_successfully_granted = "&aPomyślnie nadano &b[(amount)] &abutelek kaucyjnych!"
    var can_economy_work = "&bCzy butelki kaucyjne mogą działać na ekonomii: [(canthey)]"
    var is_economy_on = "&bCzy butelki kaucyjne aktualnie działają na ekonomii: [(on)]"
    var unknown_panel_option = "&cNieznana opcja! Opcje: &b([(options)])&c!"
    var successfully_exchanged_vault = "&aZamieniłeś butelki kaucyjne na &6[(money)]$!"
    var successfully_exchanged_item = "&aZamieniłeś butelki kaucyjne na &6[(amount)] &azłotych monetek!"

    var YES = "&aTak"
    var NO = "&cNie"
}

object Inside {
    var able_to_vault = false
    var with_vault = false
}