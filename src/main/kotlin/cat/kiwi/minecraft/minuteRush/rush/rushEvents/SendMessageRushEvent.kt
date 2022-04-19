package cat.kiwi.minecraft.minuteRush.rush.rushEvents

import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushScoreBoard
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class SendMessageRushEvent : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onAsyncPlayerChatEvent(e: AsyncPlayerChatEvent) {
        if (RushManager.taskLock) return
        Thread {
            RushManager.taskLock = true
            RushManager.currentRush?.cancel()
            SendTitle.sendAll(Lang.get("player-complete-rush",e.player))
            RushScoreBoard.inc(e.player.displayName)

            Thread.sleep(Config.delay_per_task)
            RushManager.taskLock = false
        }.start()
    }
}