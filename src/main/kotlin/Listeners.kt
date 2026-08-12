package xyz.playboy

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

// §

class WaitListener : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onJoin(event: PlayerJoinEvent) {
        val who = event.player

        if (FromConfig.allow_greeting_message) {
            who.sendMessage(fix(Messages.greeting_first))
            who.sendMessage(fix(Messages.greeting_second))
        }
    }
}

class RealListener(private val plugin: ButelkiKaucyjne) : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onMine(event: BlockBreakEvent) {
        val key = getKeyId(plugin) // zapomnialem wymazac ,,custom item id,, z poradnika xddddd

        val who = event.player
        val what = event.block

        if (what.type in getThemOres()) {
            if (onChance(getThemOres()[what.type] ?: 0)) { // what the helly
                val item = getKaucyjna(key, 1)

                who.world.dropItemNaturally(what.location, item)
                who.sendMessage(fix(Messages.kaucja_from_this_block))
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onKill(event: EntityDeathEvent) {
        val key = getKeyId(plugin)

        val victim = event.entity
        val attacker = victim.killer

        if (attacker != null) {
            if (victim.type in getThemMobs()) {
                if (onChance(getThemMobs()[victim.type] ?: 0)) {
                    val item = getKaucyjna(key, 1)

                    attacker.world.dropItemNaturally(victim.location, item)
                    attacker.sendMessage(fix(Messages.kaucja_from_this_mob))
                }
            }
        }
    }
}

class ExchangeListener(private val plugin: ButelkiKaucyjne) : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onExchange(event: PlayerInteractEvent) {
        val key = getKeyId(plugin)
        val kaucyjna = getKaucyjna(key, 1)

        val who = event.player
        val holds = who.inventory.itemInMainHand

        if (event.hand != EquipmentSlot.HAND) return
        if (!who.isSneaking) return
        if (!kaucyjna.isSimilar(holds)) return

        val alright = event.action in listOf(Action.RIGHT_CLICK_BLOCK)
        val amount = holds.amount

        if (alright) {
            if (Inside.with_vault) {
                val conversion = holds.amount * FromConfig.exchange_yes_amount
                val response = plugin.economy?.depositPlayer(who, conversion)
                if (response?.transactionSuccess() == true) {
                    who.inventory.setItemInMainHand(ItemStack.empty())
                    who.sendMessage(fix(Messages.successfully_exchanged_vault.replaceify(mapOf("money" to conversion))))
                } else {
                    who.sendMessage(fix(Messages.could_not_exchange))
                }
            } else {
                who.inventory.setItemInMainHand(ItemStack.empty())
                who.inventory.addItem(ItemStack((Material.matchMaterial(FromConfig.exchange_material) ?: Material.GOLD_NUGGET), (amount * FromConfig.exchange_no_amount)))
                who.sendMessage(fix(Messages.successfully_exchanged_item.replaceify(mapOf("amount" to amount))))
            }
        }
    }
}