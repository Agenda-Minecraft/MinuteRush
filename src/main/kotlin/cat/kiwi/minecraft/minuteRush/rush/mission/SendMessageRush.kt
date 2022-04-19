package cat.kiwi.minecraft.minuteRush.rush.mission

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushTimerTask
import cat.kiwi.minecraft.minuteRush.rush.rushEvents.SendMessageRushEvent
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scheduler.BukkitRunnable

class SendMessageRush(private val rushTitle: String, private val Duration: Int) : BukkitRunnable() {

    override fun cancel() {
        RushManager.currentTask!!.cancel()
        RushManager.currentTask = null
        RushManager.timerTaskID!!.cancel()
        RushManager.timerTaskID = null
        RushManager.currentRush = null

        AsyncPlayerChatEvent.getHandlerList().unregister(RushManager.currentTaskRegisteredListener!!);
    }

    override fun run() {
        RushManager.timerTaskID = RushTimerTask(Duration).runTaskTimer(MinuteRushPlugin.instance, 0, 20)
        RushManager.currentTaskRegisteredListener = SendMessageRushEvent()
        MinuteRushPlugin.instance.server.pluginManager.registerEvents(RushManager.currentTaskRegisteredListener as SendMessageRushEvent,MinuteRushPlugin.instance)
        SendTitle.sendAll(rushTitle)
    }


    override fun toString(): String = "SendMessageRush"
}