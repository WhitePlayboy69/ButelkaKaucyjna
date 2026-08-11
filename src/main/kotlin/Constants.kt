package xyz.playboy

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

const val PLUGIN_VERSION = "sure: 0.6.0"
const val GITHUB = "https://github.com/WhitePlayboy69/ButelkaKaucyjna/"
const val DISCORD = "turekjasnoczar"

val PLUGIN_HELP = """
    §a/informacje: §bPokazuje informacje o pluginie
    §a/nadaj <gracz?> <ilość?>: §bNadaje <ilość> butelek kaucyjnych graczowi <gracz>
    §a/panel <opcja>: §bPozwala wykonać rzeczy administratorskie związane z pluginem
""".trimIndent()

val PANEL_OPTIONS = listOf(
    "reload",
    "help",
    "check-vault"
)

fun getKeyId(plugin: ButelkiKaucyjne): NamespacedKey {
    return NamespacedKey(plugin, "kaucyjna")
}

fun getKaucyjna(key: NamespacedKey, many: Int): ItemStack {
    fun wth(text: String): Component {
        return LegacyComponentSerializer.legacySection().deserialize(text)
    }

    val item = ItemStack(Material.GLASS_BOTTLE, many)

    item.editMeta {
        it.displayName(wth(fix(FromConfig.kaucyjna_name)))

        if (!FromConfig.disable_kaucyjna_lore) {
            it.lore(
                listOf(wth(fix(FromConfig.kaucyjna_lore)))
            )
        }

        if (FromConfig.kaucyjna_with_infinity) {
            it.addEnchant(Enchantment.ARROW_INFINITE, 1, true)
        }

        it.persistentDataContainer.set(key, PersistentDataType.STRING, "queren")

        // ^^^
        // jak coś queren w chińskim to potwierdzony.
        // Nie jestem chinczykiem po prostu po chinsku to brzmi lepiej niz verified czy cos xd
    }

    return item
}

// hej hej co tam a dobrze dzieki