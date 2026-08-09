package xyz.playboy

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

const val PLUGIN_VERSION = "sure: 0.3.0"
const val GITHUB = "https://github.com/WhitePlayboy69/ButelkaKaucyjna/"
const val DISCORD = "turekjasnoczar"

val PLUGIN_HELP = """
    §a/informacje: §bPokazuje informacje o pluginie
    §a/nadaj <gracz?> <ilość?>: §bNadaje <ilość> butelek kaucyjnych graczowi <gracz>
    §a/panel <opcja>: §bPozwala wykonać rzeczy administratorskie związane z pluginem
""".trimIndent()

val KAUCJA_QUALIFIED = mapOf(
    Material.COAL_ORE to 20,
    Material.DEEPSLATE_COAL_ORE to 20,
    Material.COPPER_ORE to 15,
    Material.DEEPSLATE_COPPER_ORE to 20,
    Material.IRON_ORE to 30,
    Material.DEEPSLATE_IRON_ORE to 30,
    Material.GOLD_ORE to 30,
    Material.DEEPSLATE_GOLD_ORE to 30,
    Material.EMERALD_ORE to 60,
    Material.DEEPSLATE_EMERALD_ORE to 60,
    Material.REDSTONE_ORE to 20,
    Material.DEEPSLATE_REDSTONE_ORE to 20,
    Material.LAPIS_ORE to 15,
    Material.DEEPSLATE_LAPIS_ORE to 15,
    Material.DIAMOND_ORE to 50,
    Material.DEEPSLATE_DIAMOND_ORE to 50,
    Material.ANCIENT_DEBRIS to 60
)

val PANEL_OPTIONS = listOf(
    "reload",
    "help"
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

    return item
}

// hej hej co tam a dobrze dzieki