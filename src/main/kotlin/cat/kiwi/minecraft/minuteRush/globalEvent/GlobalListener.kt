package cat.kiwi.minecraft.minuteRush.globalEvent

import cat.kiwi.minecraft.metcd.MEtcd
import cat.kiwi.minecraft.metcd.model.GameStatus
import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.dispaly.RushScoreBoard
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class GlobalListener : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onPlayerJoinEvent(e: PlayerJoinEvent) {
        if (MEtcd.getGameStatus() == GameStatus.RUNNING) {
            e.player.kickPlayer("Game is running")
            return
        }
        if (MEtcd.getGameStatus() == GameStatus.ENDING) {
            e.player.kickPlayer("Game is ending")
            return
        }
        if (Bukkit.getOnlinePlayers().size >= Config.startPlayer) {
            RushManager.startRush()
        }

        val player = e.player.displayName
        if (RushScoreBoard.playerMapScore[player] == null) {
            RushScoreBoard.playerMapScore[player] = 0
        }

        RushScoreBoard.flush()
    }
}