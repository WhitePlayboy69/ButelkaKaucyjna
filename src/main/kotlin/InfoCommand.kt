package xyz.playboy

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

// §§§§§§§§§§§§§§§§§§§§

class InfoCommand : CommandExecutor {
    override fun onCommand(guy: CommandSender, what: Command, label: String, args: Array<out String>?): Boolean {
        guy.sendMessage(
            """
               §a--- BUTELKI KAUCYJNE ---
               §6Wersja: §b${PLUGIN_VERSION}
               §6Github: §b${GITHUB}
               §6Discord: §b${DISCORD}
               §a------------------------
            """.trimIndent()
        )

        return true
    }
}