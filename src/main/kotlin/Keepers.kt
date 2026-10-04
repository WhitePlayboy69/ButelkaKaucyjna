package xyz.playboy

object FromConfig {
    var language = "en_us"

    var allow_greeting_message = true
    var kaucyjna_with_infinity = true
    var disable_kaucyjna_lore = false

    var kaucyjna_name = "&6Deposit bottle"
    var kaucyjna_lore = "&fBottle, which you can exchange by clicking &7Shift + Right Click&f."

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
    var insufficient_permission = "&cYou don't have sufficient permissions to do this!"
    var successfully_config_reloaded = "&aConfig was successfully reloaded!"
    var player_is_offline = "&cThis player is offline!"
    var kaucja_successfully_given = "&aSuccessfully granted a deposit bottle!"
    var wrong_args_nadaj = "&cCorrect usage: &f/nadaj <player> <amount>&c!"
    var greeting_first = "&aWelcome to the server!"
    var greeting_second = "&aGet &bdeposit bottles &aby mining and killing mobs!"
    var kaucja_from_this_block = "&aYou got a deposit bottle from this block!"
    var kaucja_from_this_mob = "&aYou got a deposit bottle from this mob!"
    var could_not_exchange = "&cCouldn't exchange deposit bottles! Contact with server's administrator."

    var kaucja_successfully_granted = "&aSuccessfully granted &b[(amount)] &adeposit bottles!"
    var can_economy_work = "&bCan deposit bottles work on Vault: [(toggle)]"
    var is_economy_on = "&bDo deposit bottles work on Vault: [(toggle)]"
    var unknown_panel_option = "&cUnknown option! Possible options: &b([(options)])&c!"
    var successfully_exchanged_vault = "&aSuccessfully exchanged deposit bottles for &6[(money)]$!"
    var successfully_exchanged_item = "&aSuccessfully exchanged deposit bottles for &6[(amount)] &agolden nuggets!"

    var YES = "&aYes"
    var NO = "&cNo"
}

object Inside {
    var able_to_vault = false
    var with_vault = false
}