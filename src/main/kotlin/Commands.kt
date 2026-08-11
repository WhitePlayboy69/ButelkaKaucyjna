package xyz.playboy

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

// §§§§§§§§§§§§§§§§§§§§

class InfoCommand : CommandExecutor {
    override fun onCommand(guy: CommandSender, what: Command, label: String, args: Array<out String>?): Boolean {
        guy.sendMessage(
            """
               §a--- BUTELKI KAUCYJNE ---
               §6Wersja: §b${PLUGIN_VERSION}
               §6Github: §b${GITHUB}
               §6Discord: §b${DISCORD}
               §a-----------------------
            """.trimIndent()
        )

        return true
    }
}

class NadajCommand(private val plugin: ButelkiKaucyjne) : CommandExecutor {
    override fun onCommand(guy: CommandSender, what: Command, label: String, args: Array<out String>?): Boolean {
        val key = getKeyId(plugin)

        val rawPlayer = args?.getOrNull(0)
        val rawMany = args?.getOrNull(1)?.toIntOrNull()
        val player: Player?
        val many: Int?

        if (rawPlayer != null) {
            player = Bukkit.getPlayerExact(rawPlayer)
        } else {
            if (guy is Player) {
                player = guy
            } else {
                guy.sendMessage("§cPoprawne użycie: §f/nadaj <gracz> <ilość>§c!")
                return true
            }
        } // nie ma return if bo boli w oczy

        if (player != null) {
            many = rawMany ?: 1
        } else {
            guy.sendMessage("§cTen gracz jest offline!")
            return true
        }

        val item = getKaucyjna(key, many)

        player.inventory.addItem(item)

        if (many == 1) {
            guy.sendMessage("§aPomyślnie nadano butelkę kaucyjną!")
        } else {
            guy.sendMessage("§aPomyślnie nadano §b${many} §abutelek kaucyjnych!")
        }

        return true
    }
}

class PanelCommand(private val plugin: ButelkiKaucyjne) : TabExecutor {
    override fun onCommand(guy: CommandSender, what: Command, label: String, args: Array<out String>?): Boolean {
        val option = args?.getOrNull(0)

        if (option in PANEL_OPTIONS) {
            when (option) {
                "reload" -> {
                    plugin.reloadConfig()
                    plugin.secondStep()
                    guy.sendMessage("§aPomyślnie zreloadowano config!")
                }
                "help" -> {
                    guy.sendMessage(PLUGIN_HELP)
                }
                "check-vault" -> {
                    guy.sendMessage("§bCzy butelki kaucyjne działają na ekonomii: ${Inside.with_vault.humanify()}")
                }
            }
        } else {
            guy.sendMessage("§cNieznana opcja! Opcje: §b(${PANEL_OPTIONS.joinToString(", ")})§c!")
        }

        return true
    }

    override fun onTabComplete(
        who: CommandSender,
        what: Command,
        alias: String,
        args: Array<out String>?
    ): List<String> {
        return when (args?.size) {
            1 -> PANEL_OPTIONS.complete(args[0])
            else -> emptyList()
            // huj
        }
    }
}