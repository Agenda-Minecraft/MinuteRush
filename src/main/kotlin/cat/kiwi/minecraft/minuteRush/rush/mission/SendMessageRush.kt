package cat.kiwi.minecraft.minuteRush.rush.mission

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushTimerTask
import cat.kiwi.minecraft.minuteRush.rush.rushEvents.SendMessageRushEvent
import cat.kiwi.minecraft.minuteRush.dispaly.SendTitle
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class SendMessageRush(private val rushTitle: String, private val Duration: Int) : BukkitRunnable() {
    private var timerTaskID: BukkitTask? = null
        set(value) = run { field = value }

    override fun cancel() {
        RushManager.currentRushTask!!.cancel()
        RushManager.currentRushTask = null
        timerTaskID!!.cancel()
        RushManager.currentRush = null

        AsyncPlayerChatEvent.getHandlerList().unregister(RushManager.currentTaskRegisteredListener!!);
    }

    override fun run() {
        timerTaskID = RushTimerTask(Duration).runTaskTimer(MinuteRushPlugin.instance, 0, 20)
        RushManager.currentTaskRegisteredListener = SendMessageRushEvent()
        MinuteRushPlugin.instance.server.pluginManager.registerEvents(RushManager.currentTaskRegisteredListener as SendMessageRushEvent,MinuteRushPlugin.instance)
        SendTitle.sendAll(rushTitle)
    }


    override fun toString(): String = "SendMessageRush"
}