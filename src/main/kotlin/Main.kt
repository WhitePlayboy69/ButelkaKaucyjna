package xyz.playboy

import org.bukkit.plugin.java.JavaPlugin

class ButelkiKaucyjne : JavaPlugin() {
    fun secondStep() {
        FromConfig.allow_greeting_message = config.getBoolean("allow-greeting-message")
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