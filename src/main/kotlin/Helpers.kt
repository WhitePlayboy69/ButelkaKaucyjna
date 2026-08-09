package xyz.playboy

import kotlin.random.Random

fun onChance(chance: Int): Boolean {
    if (chance <= 0) return false
    if (chance >= 100) return true
    return Random.nextInt(100) < chance
}