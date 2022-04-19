package cat.kiwi.minecraft.minuteRush.rush.rushEvents

import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushScoreBoard
import cat.kiwi.minecraft.minuteRush.rush.mission.StandOnBlockRush
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class StandOnBlockRushEvent : Listener {
    @EventHandler(ignoreCancelled = false)
    fun onPlayerMoveEvent(e: PlayerMoveEvent) {
        if (RushManager.taskLock) return
        Thread {
            // check weather player stand on block
            if (RushManager.currentRush is StandOnBlockRush) {
                val material = (RushManager.currentRush as StandOnBlockRush).material
                val playerLoc = e.player.location
                playerLoc.y = playerLoc.y - 1
                if (playerLoc.block.type == (RushManager.currentRush as StandOnBlockRush).material) {
                    RushManager.taskLock = true
                    RushManager.currentRush?.cancel()
                    SendTitle.sendAll(Lang.get("player-complete-rush", e.player))
                    RushScoreBoard.inc(e.player.displayName)
                    Thread.sleep(2000)
                    RushManager.taskLock = false
                }
            }
        }.start()
    }
}
