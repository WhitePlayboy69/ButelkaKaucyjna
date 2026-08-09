package xyz.playboy

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerJoinEvent

// §

class WaitListener : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onJoin(event: PlayerJoinEvent) {
        val who = event.player

        if (FromConfig.allow_greeting_message) {
            who.sendMessage("§aWitaj na serwerze!")
            who.sendMessage("§aZbieraj §bbutelki kaucyjne §akopiąc!")
        }
    }
}

class RealListener(private val plugin: ButelkiKaucyjne) : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onMine(event: BlockBreakEvent) {
        val key = getKeyId(plugin) // zapomnialem wymazac ,,custom item id,, z poradnika xddddd

        val who = event.player
        val what = event.block

        if (what.type in KAUCJA_QUALIFIED) {
            if (onChance(KAUCJA_QUALIFIED[what.type] ?: 0)) { // what the helly
                val item = getKaucyjna(key, 1)

                who.world.dropItemNaturally(what.location, item)
                who.sendMessage("§aZdobyłeś kaucję z tego bloku!")
            }
        }
    }
}