package xyz.playboy

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration

fun associateMessages(yaml: YamlConfiguration) {
    Messages.insufficient_permission = yaml.getString("messages.technical.insufficient-permissions") ?: "&cYou don't have sufficient permissions to do this!"
    Messages.successfully_config_reloaded = yaml.getString("messages.technical.successfully-config-reloaded") ?: "&aConfig was successfully reloaded!"
    Messages.player_is_offline = yaml.getString("messages.technical.player-is-offline") ?: "&cThis player is offline!"
    Messages.kaucja_successfully_given = yaml.getString("messages.gameplay.kaucja-successfully-given") ?: "&aSuccessfully granted a deposit bottle!"
    Messages.wrong_args_nadaj = yaml.getString("messages.technical.wrong-args-nadaj") ?: "&cCorrect usage: &f/nadaj <player> <amount>&c!"
    Messages.greeting_first = yaml.getString("messages.technical.greeting-first") ?: "&aWelcome to the server!"
    Messages.greeting_second = yaml.getString("messages.technical.greeting-second") ?: "&aGet &bdeposit bottles &aby mining and killing mobs!"
    Messages.kaucja_from_this_block = yaml.getString("messages.gameplay.kaucja-from-this-block") ?: "&aYou got a deposit bottle from this block!"
    Messages.kaucja_from_this_mob = yaml.getString("messages.gameplay.kaucja-from-this-mob") ?: "&aYou got a deposit bottle from this mob!"
    Messages.could_not_exchange = yaml.getString("messages.gameplay.could-not-exchange") ?: "&cCouldn't exchange deposit bottles! Contact with server's administrator."

    Messages.kaucja_successfully_granted = yaml.getString("messages.technical.kaucja-successfully-granted") ?: "&aSuccessfully granted &b[(amount)] &adeposit bottles!"
    Messages.can_economy_work = yaml.getString("messages.economy.can-economy-work") ?: "&bCan deposit bottles work on Vault: [(toggle)]"
    Messages.is_economy_on = yaml.getString("messages.economy.is-economy-on") ?: "&bDo deposit bottles work on Vault: [(toggle)]"
    Messages.unknown_panel_option = yaml.getString("messages.technical.unknown-panel-option") ?: "Unknown option! Possible options: &b([(options)])&c!"
    Messages.successfully_exchanged_vault = yaml.getString("messages.gameplay.successfully-exchanged-vault") ?: "&aSuccessfully exchanged deposit bottles for &6[(money)]$!"
    Messages.successfully_exchanged_item = yaml.getString("messages.gameplay.successfully-exchanged-item") ?: "&aSuccessfully exchanged deposit bottles for &6[(amount)] golden nuggets!"

    FromConfig.kaucyjna_name = yaml.getString("messages.other.kaucyjna-name") ?: "&6Deposit bottle"
    FromConfig.kaucyjna_lore = yaml.getString("messages.other.kaucyjna-lore") ?: "&fBottle, which you can exchange by clicking &7Shift + Right Click&f."
    Messages.YES = yaml.getString("messages.other.easy-yes") ?: "&aYes"
    Messages.NO = yaml.getString("messages.other.easy-no") ?: "&cNo"
}

fun associateConfig(config: FileConfiguration) {
    FromConfig.language = config.getString("language") ?: "en_us"

    FromConfig.allow_greeting_message = config.getBoolean("allow-greeting-message")
    FromConfig.kaucyjna_with_infinity = config.getBoolean("kaucyjna-with-infinity")
    FromConfig.disable_kaucyjna_lore = config.getBoolean("disable-kaucyjna-lore")

    Inside.with_vault = Inside.able_to_vault && config.getBoolean("use-vault-if-possible")

    // CHANCES

    FromConfig.coal_ore_chance = config.getInt("chance-coal-ore")
    FromConfig.copper_ore_chance = config.getInt("chance-copper-ore")
    FromConfig.iron_ore_chance = config.getInt("chance-iron-ore")
    FromConfig.gold_ore_chance = config.getInt("chance-gold-ore")
    FromConfig.emerald_ore_chance = config.getInt("chance-emerald-ore")
    FromConfig.redstone_ore_chance = config.getInt("chance-redstone-ore")
    FromConfig.lapis_ore_chance = config.getInt("chance-lapis-ore")
    FromConfig.diamond_ore_chance = config.getInt("chance-diamond-ore")
    FromConfig.ancient_debris_chance = config.getInt("chance-ancient-debris")

    FromConfig.zombie_chance = config.getInt("chance-zombie-kill")
    FromConfig.skeleton_chance = config.getInt("chance-skeleton-kill")
    FromConfig.creeper_chance = config.getInt("chance-creeper-kill")
    FromConfig.spider_chance = config.getInt("chance-spider-kill")
    FromConfig.enderman_chance = config.getInt("chance-enderman-kill")

    // CHANCES

    FromConfig.exchange_material = config.getString("exchange-material") ?: "gold_nugget"
    FromConfig.exchange_no_amount = config.getInt("exchange-no-amount")
    FromConfig.exchange_yes_amount = config.getDouble("exchange-yes-amount")
}