package xyz.playboy

import org.bukkit.plugin.java.JavaPlugin

class ButelkiKaucyjne : JavaPlugin() {
    override fun onEnable() {
        getCommand("informacje")?.setExecutor(InfoCommand())
        getCommand("nadaj")?.setExecutor(NadajCommand(this))

        server.pluginManager.registerEvents(WaitListener(), this)
        server.pluginManager.registerEvents(RealListener(), this)
    }
}