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
        if (guy.hasPermission("butelki.nadaj")) {
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
                    guy.sendMessage(fix(Messages.wrong_args_nadaj))
                    return true
                }
            } // nie ma return if bo boli w oczy

            if (player != null) {
                many = rawMany ?: 1
            } else {
                guy.sendMessage(fix(Messages.player_is_offline))
                return true
            }

            val item = getKaucyjna(key, many)

            player.inventory.addItem(item)

            if (many == 1) {
                guy.sendMessage(fix(Messages.kaucja_successfully_given))
            } else {
                guy.sendMessage(fix(Messages.kaucja_successfully_granted.replaceify(mapOf("amount" to many))))
            }
        } else {
            guy.sendMessage(fix(Messages.unsufficient_permission))
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
                    if (guy.hasPermission("butelki.panel.reload")) {
                        plugin.reloadConfig()
                        plugin.secondStep()
                        plugin.thirdStep()
                        guy.sendMessage(fix(Messages.successfully_config_reloaded))
                    } else {
                        guy.sendMessage(fix(Messages.unsufficient_permission))
                    }
                }
                "help" -> {
                    if (guy.hasPermission("butelki.panel.help")) {
                        guy.sendMessage(PLUGIN_HELP)
                    } else {
                        guy.sendMessage(fix(Messages.unsufficient_permission))
                    }
                }
                "check-vault" -> {
                    if (guy.hasPermission("butelki.panel.check-vault")) {
                        guy.sendMessage(fix(Messages.can_economy_work.replaceify(mapOf("toggle" to Inside.able_to_vault.humanify()))))
                        guy.sendMessage(fix(Messages.is_economy_on.replaceify(mapOf("toggle" to Inside.with_vault.humanify()))))
                    } else {
                        guy.sendMessage(fix(Messages.unsufficient_permission))
                    }
                }
            }
        } else {
            guy.sendMessage(fix(Messages.unknown_panel_option.replaceify(mapOf("options" to PANEL_OPTIONS.joinToString(", ")))))
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