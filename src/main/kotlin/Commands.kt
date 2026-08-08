package xyz.playboy

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

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

class NadajCommand(private val plugin: ButelkiKaucyjne) : CommandExecutor {
    override fun onCommand(guy: CommandSender, what: Command, label: String, args: Array<out String>?): Boolean {
        fun wth(text: String): Component {
            return LegacyComponentSerializer.legacySection().deserialize(text)
        }

        val key = NamespacedKey(plugin, "custom_item_id")

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

        val item = ItemStack(Material.GLASS_BOTTLE, many)

        item.editMeta {
            it.displayName(wth("§6Butelka kaucyjna"))
            it.lore(
                listOf(wth("§fButelka, którą można wymienić w butelkomacie."))
            )

            it.addEnchant(Enchantment.ARROW_INFINITE, 1, true)
            it.persistentDataContainer.set(key, PersistentDataType.STRING, "queren")

            // ^^^
            // jak coś queren w chińskim to potwierdzony.
            // Nie jestem chinczykiem po prostu po chinsku to brzmi lepiej niz verified czy cos xd
        }

        player.inventory.addItem(item)

        if (many == 1) {
            guy.sendMessage("§aPomyślnie nadano butelkę kaucyjną!")
        } else {
            guy.sendMessage("§aPomyślnie nadano §b${many} §abutelek kaucyjnych!")
        }

        return true
    }
}