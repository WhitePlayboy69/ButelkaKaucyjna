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

        // ^ useless but whatever

        val yaml = File(dataFolder, "languages/${FromConfig.language}.yml")
        languageConfig = YamlConfiguration.loadConfiguration(yaml)
    }

    fun secondStep() {
        associateConfig(config)
    }

    fun thirdStep() {
        associateMessages(languageConfig)
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