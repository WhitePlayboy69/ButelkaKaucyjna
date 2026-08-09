package xyz.playboy

import org.bukkit.plugin.java.JavaPlugin

class ButelkiKaucyjne : JavaPlugin() {
    fun secondStep() {
        FromConfig.allow_greeting_message = config.getBoolean("allow-greeting-message")

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
    }

    override fun onEnable() {
        saveDefaultConfig()
        secondStep()

        getCommand("informacje")?.setExecutor(InfoCommand())
        getCommand("nadaj")?.setExecutor(NadajCommand(this))
        getCommand("panel")?.setExecutor(PanelCommand(this))

        server.pluginManager.registerEvents(WaitListener(), this)
        server.pluginManager.registerEvents(RealListener(this), this)
    }
}