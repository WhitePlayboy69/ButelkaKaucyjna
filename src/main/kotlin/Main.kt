package xyz.playboy

import org.bukkit.plugin.java.JavaPlugin
import net.milkbowl.vault.economy.Economy

class ButelkiKaucyjne : JavaPlugin() {
    var economy: Economy? = null
        private set

    fun secondStep() {
        FromConfig.allow_greeting_message = config.getBoolean("allow-greeting-message")
        FromConfig.kaucyjna_with_infinity = config.getBoolean("kaucyjna-with-infinity")
        FromConfig.disable_kaucyjna_lore = config.getBoolean("disable-kaucyjna-lore")
        FromConfig.kaucyjna_name = config.getString("kaucyjna-name") ?: "&6Butelka kaucyjna"
        FromConfig.kaucyjna_lore = config.getString("kaucyjna-lore") ?: "&fButelka, którą można wymienić w butelkomacie."

        Inside.with_vault = if (Inside.able_to_vault) config.getBoolean("use-vault-if-possible") else false

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
        Messages.unsufficient_permission = config.getString("message-unsufficient-permissions") ?: "&cNie masz wystarczających permisji by to zrobić!"
        Messages.successfully_config_reloaded = config.getString("message-successfully-config-reloaded") ?: "&aPomyślnie zreloadowano config!"
        Messages.player_is_offline = config.getString("message-player-is-offline") ?: "&cTen gracz jest offline!"
        Messages.kaucja_successfully_given = config.getString("message-kaucja-successfully-given") ?: "&aPomyślnie nadano butelkę kaucyjną!"
        Messages.wrong_args_nadaj = config.getString("message-wrong-args-nadaj") ?: "&cPoprawne użycie: &f/nadaj <gracz> <ilość>&c!"
        Messages.greeting_first = config.getString("message-greeting-first") ?: "&aWitaj na serwerze!"
        Messages.greeting_second = config.getString("message-greeting-second") ?: "&aZbieraj &bbutelki kaucyjne &akopiąc i zabijając!"
        Messages.kaucja_from_this_block = config.getString("message-kaucja-from-this-block") ?: "&aZdobyłeś kaucję z tego bloku!"
        Messages.kaucja_from_this_mob = config.getString("message-kaucja-from-this-mob") ?: "&aZdobyłeś kaucję z tego moba!"
        Messages.could_not_exchange = config.getString("message-could-not-exchange") ?: "&cNie można było wymienić butelek kaucyjnych! Skontaktuj się z administratorem."

        Messages.kaucja_successfully_granted = config.getString("message-kaucja-successfully-granted") ?: "&aPomyślnie nadano &b[(amount)] &abutelek kaucyjnych!"
        Messages.can_economy_work = config.getString("message-can-economy-work") ?: "&bCzy butelki kaucyjne mogą działać na ekonomii: [(canthey)]"
        Messages.is_economy_on = config.getString("message-is-economy-on") ?: "&bCzy butelki kaucyjne aktualnie działają na ekonomii: [(on)]"
        Messages.unknown_panel_option = config.getString("message-unknown-panel-option") ?: "&cNieznana opcja! Opcje: &b([(options)])&c!"
        Messages.successfully_exchanged_vault = config.getString("message-successfully-exchanged-vault") ?: "&aZamieniłeś butelki kaucyjne na &6[(money)]$!"
        Messages.successfully_exchanged_item = config.getString("message-successfully-exchanged-item") ?: "&aZamieniłeś butelki kaucyjne na &6[(amount)] &azłotych monetek!"

        Messages.YES = config.getString("message-yes") ?: "&aTak"
        Messages.NO = config.getString("message-no") ?: "&cNie"
    }

    private fun makeThemVault(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }

        val rsp = server.servicesManager.getRegistration(Economy::class.java) ?: return false
        economy = rsp.provider

        return economy != null
    }

    override fun onEnable() {
        Inside.able_to_vault = makeThemVault()

        saveDefaultConfig()
        secondStep()
        thirdStep()

        getCommand("informacje")?.setExecutor(InfoCommand())
        getCommand("nadaj")?.setExecutor(NadajCommand(this))
        getCommand("panel")?.setExecutor(PanelCommand(this))

        server.pluginManager.registerEvents(WaitListener(), this)
        server.pluginManager.registerEvents(RealListener(this), this)
        server.pluginManager.registerEvents(ExchangeListener(this), this)
    }
}