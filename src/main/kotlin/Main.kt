package xyz.playboy

import org.bukkit.plugin.java.JavaPlugin
import net.milkbowl.vault.economy.Economy
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ButelkiKaucyjne : JavaPlugin() {
    lateinit var languageConfig: YamlConfiguration
        private set

    val baller = logger // i hate this name
    var economy: Economy? = null
        private set

    fun language() {
        val lick = File(dataFolder, "languages")
        if (!lick.exists()) {
            lick.mkdirs()
        }

        LANGUAGES.forEach { language ->
            val name = "${language}.yml"
            val file = File(dataFolder, "languages/${name}")
            if (!file.exists()) {
                saveResource("languages/${name}", false)
                baller.info("Language file \"${name}\" was created successfully!")
            } else {
                baller.info("Language file \"${name}\" already exists! (nothing wrong)")
            }
        }

        val yaml = File(dataFolder, "languages/${FromConfig.language}.yml")
        languageConfig = YamlConfiguration.loadConfiguration(yaml)
    }

    fun secondStep() {
        FromConfig.language = config.getString("language") ?: "en_us"

        FromConfig.allow_greeting_message = config.getBoolean("allow-greeting-message")
        FromConfig.kaucyjna_with_infinity = config.getBoolean("kaucyjna-with-infinity")
        FromConfig.disable_kaucyjna_lore = config.getBoolean("disable-kaucyjna-lore")
        FromConfig.kaucyjna_name = config.getString("kaucyjna-name") ?: "&6Butelka kaucyjna"
        FromConfig.kaucyjna_lore = config.getString("kaucyjna-lore") ?: "&fButelka, którą można wymienić w butelkomacie."

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

    fun thirdStep() {
        Messages.unsufficient_permission = languageConfig.getString("messages.technical.unsufficient-permissions") ?: "&cYou don't have sufficient permissions to do this!"
        Messages.successfully_config_reloaded = languageConfig.getString("messages.technical.successfully-config-reloaded") ?: "&aConfig was successfully reloaded!"
        Messages.player_is_offline = languageConfig.getString("messages.technical.player-is-offline") ?: "&cThis player is offline!"
        Messages.kaucja_successfully_given = languageConfig.getString("messages.gameplay.kaucja-successfully-given") ?: "&aSuccessfully granted a deposit bottle!"
        Messages.wrong_args_nadaj = languageConfig.getString("messages.technical.wrong-args-nadaj") ?: "&cCorrect usage: &f/nadaj <player> <amount>&c!"
        Messages.greeting_first = languageConfig.getString("messages.technical.greeting-first") ?: "&aWelcome to the server!"
        Messages.greeting_second = languageConfig.getString("messages.technical.greeting-second") ?: "&aGet &bdeposit bottles &aby mining and killing mobs!"
        Messages.kaucja_from_this_block = languageConfig.getString("messages.gameplay.kaucja-from-this-block") ?: "&aYou got a deposit bottle from this block!"
        Messages.kaucja_from_this_mob = languageConfig.getString("messages.gameplay.kaucja-from-this-mob") ?: "&aYou got a deposit bottle from this mob!"
        Messages.could_not_exchange = languageConfig.getString("messages.gameplay.could-not-exchange") ?: "&cCouldn't exchange deposit bottles! Contact with server's administrator."

        Messages.kaucja_successfully_granted = languageConfig.getString("messages.technical.kaucja-successfully-granted") ?: "&aSuccessfully granted &b[(amount)] &adeposit bottles!"
        Messages.can_economy_work = languageConfig.getString("messages.economy.can-economy-work") ?: "&bCan deposit bottles work on Vault: [(toggle)]"
        Messages.is_economy_on = languageConfig.getString("messages.economy.is-economy-on") ?: "&bDo deposit bottles work on Vault: [(toggle)]"
        Messages.unknown_panel_option = languageConfig.getString("messages.technical.unknown-panel-option") ?: "Unknown option! Possible options: &b([(options)])&c!"
        Messages.successfully_exchanged_vault = languageConfig.getString("messages.gameplay.successfully-exchanged-vault") ?: "&aSuccessfully exchanged deposit bottles for &6[(money)]$!"
        Messages.successfully_exchanged_item = languageConfig.getString("messages.gameplay.successfully-exchanged-item") ?: "&aSuccessfully exchanged deposit bottles for &6[(amount)] golden nuggets!"

        Messages.YES = languageConfig.getString("messages.other.yes") ?: "&aYes"
        Messages.NO = languageConfig.getString("messages.other.no") ?: "&cNo"
    }

    private fun makeThemVault(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }

        val rsp = server.servicesManager.getRegistration(Economy::class.java) ?: return false
        economy = rsp.provider

        return true
    }

    override fun onEnable() {
        Inside.able_to_vault = makeThemVault()

        saveDefaultConfig()
        secondStep()
        language()
        thirdStep()

        getCommand("informacje")?.setExecutor(InfoCommand())
        getCommand("nadaj")?.setExecutor(NadajCommand(this))
        getCommand("panel")?.setExecutor(PanelCommand(this))

        server.pluginManager.registerEvents(WaitListener(), this)
        server.pluginManager.registerEvents(RealListener(this), this)
        server.pluginManager.registerEvents(ExchangeListener(this), this)
    }
}