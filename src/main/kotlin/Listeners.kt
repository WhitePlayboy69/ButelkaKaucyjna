package xyz.playboy

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

        who.sendMessage("§aWitaj na serwerze!")
        who.sendMessage("§aZbieraj §bbutelki kaucyjne §akopiąc!")
    }
}

class RealListener : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onMine(event: BlockBreakEvent) {
        val who = event.player
        val what = event.block.type

        if (what in KAUCJA_QUALIFIED) {
            who.sendMessage("§aTen blok nadaje się do kaucji!")
        }
    }
}