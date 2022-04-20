package cat.kiwi.minecraft.minuteRush.globalEvent

import cat.kiwi.minecraft.minuteRush.dispaly.RushScoreBoard
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class GlobalListener : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerJoinEvent(e: PlayerJoinEvent) {
        val player = e.player.displayName
        if (RushScoreBoard.playerMapScore[player] == null) {
            RushScoreBoard.playerMapScore[player] = 0
        }

        RushScoreBoard.flush()
    }
}