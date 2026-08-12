package xyz.playboy

import org.bukkit.Material
import org.bukkit.entity.EntityType
import kotlin.random.Random

fun onChance(chance: Int): Boolean {
    if (chance <= 0) return false
    if (chance >= 100) return true
    return Random.nextInt(100) < chance
}

fun getThemOres(): Map<Material, Int> {
    val KAUCJA_QUALIFIED = mapOf(
        Material.COAL_ORE to FromConfig.coal_ore_chance,
        Material.DEEPSLATE_COAL_ORE to FromConfig.coal_ore_chance,
        Material.COPPER_ORE to FromConfig.copper_ore_chance,
        Material.DEEPSLATE_COPPER_ORE to FromConfig.copper_ore_chance,
        Material.IRON_ORE to FromConfig.iron_ore_chance,
        Material.DEEPSLATE_IRON_ORE to FromConfig.iron_ore_chance,
        Material.GOLD_ORE to FromConfig.gold_ore_chance,
        Material.DEEPSLATE_GOLD_ORE to FromConfig.gold_ore_chance,
        Material.EMERALD_ORE to FromConfig.emerald_ore_chance,
        Material.DEEPSLATE_EMERALD_ORE to FromConfig.emerald_ore_chance,
        Material.REDSTONE_ORE to FromConfig.redstone_ore_chance,
        Material.DEEPSLATE_REDSTONE_ORE to FromConfig.redstone_ore_chance,
        Material.LAPIS_ORE to FromConfig.lapis_ore_chance,
        Material.DEEPSLATE_LAPIS_ORE to FromConfig.lapis_ore_chance,
        Material.DIAMOND_ORE to FromConfig.diamond_ore_chance,
        Material.DEEPSLATE_DIAMOND_ORE to FromConfig.diamond_ore_chance,
        Material.ANCIENT_DEBRIS to FromConfig.ancient_debris_chance
    )

    return KAUCJA_QUALIFIED
}

fun getThemMobs(): Map<EntityType, Int> = mapOf(
    EntityType.ZOMBIE to FromConfig.zombie_chance,
    EntityType.SKELETON to FromConfig.skeleton_chance,
    EntityType.CREEPER to FromConfig.creeper_chance,
    EntityType.SPIDER to FromConfig.spider_chance,
    EntityType.ENDERMAN to FromConfig.enderman_chance
)

fun fix(text: String): String {
    return text.replace("&", "§")
}

fun List<String>.complete(arg: String): List<String> =
    filter { it.startsWith(arg, ignoreCase = true) }

fun Boolean.humanify(): String {
    return if (this) fix(Messages.YES) else fix(Messages.NO)
}

fun String.replaceify(data: Map<String, Any>): String {
    val regex = Regex("""\[\(([^)]+)\)]""") // ouu shii
    return regex.replace(this) { match ->
        val key = match.groupValues[1]
        data[key]?.toString() ?: match.value
    }
}