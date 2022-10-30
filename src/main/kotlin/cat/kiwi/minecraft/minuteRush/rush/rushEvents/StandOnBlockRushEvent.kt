package cat.kiwi.minecraft.minuteRush.rush.rushEvents

import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.dispaly.RushScoreBoard
import cat.kiwi.minecraft.minuteRush.rush.mission.StandOnBlockRushContext
import cat.kiwi.minecraft.minuteRush.dispaly.SendTitle
import cat.kiwi.minecraft.minuteRush.utils.Logger
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class StandOnBlockRushEvent : Listener {
    @EventHandler(ignoreCancelled = false)
    fun onPlayerMoveEvent(e: PlayerMoveEvent) {
        if (RushManager.taskLock) return
        Thread {
            // check weather player stand on block
            if (RushManager.currentRush is StandOnBlockRushContext) {
                val material = (RushManager.currentRush as StandOnBlockRushContext).material
                val playerLoc = e.player.location
                playerLoc.y = playerLoc.y - 1
                Logger.debug("playerLoc: $playerLoc")
                Logger.debug("playerLoc.block.type: ${playerLoc.block.type}")
                if (playerLoc.block.type == material) {
                    RushManager.taskLock = true
                    if (RushManager.currentRush != null) {
                        RushManager.currentRush!!.cancel()
                    }
                    SendTitle.sendAll(Lang.get("player.complete-rush", e.player))
                    RushScoreBoard.inc(e.player.displayName)
                    Thread.sleep(2000)
                    RushManager.taskLock = false
                }
            }
        }.start()
    }
}
