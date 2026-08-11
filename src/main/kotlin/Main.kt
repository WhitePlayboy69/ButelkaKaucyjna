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

    private fun makeThemVault(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }

        val rsp = server.servicesManager.getRegistration(Economy::class.java) ?: return false
        economy = rsp.provider

        return economy != null
    }

    override fun onEnable() {
        saveDefaultConfig()
        secondStep()

        Inside.with_vault = makeThemVault()

        getCommand("informacje")?.setExecutor(InfoCommand())
        getCommand("nadaj")?.setExecutor(NadajCommand(this))
        getCommand("panel")?.setExecutor(PanelCommand(this))

        server.pluginManager.registerEvents(WaitListener(), this)
        server.pluginManager.registerEvents(RealListener(this), this)
        server.pluginManager.registerEvents(ExchangeListener(this), this)
    }
}