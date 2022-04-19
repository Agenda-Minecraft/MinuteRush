package cat.kiwi.minecraft.minuteRush.rush.mission

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushTimerTask
import cat.kiwi.minecraft.minuteRush.rush.rushEvents.StandOnBlockRushEvent
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import org.bukkit.Material
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable

class StandOnBlockRush(private val rushTitle: String, private val duration:Int, private val block: Material):BukkitRunnable() {
    val material get() = block
    override fun cancel() {
        RushManager.currentTask!!.cancel()
        RushManager.currentTask = null
        RushManager.timerTaskID!!.cancel()
        RushManager.timerTaskID = null
        RushManager.currentRush = null

        PlayerMoveEvent.getHandlerList().unregister(RushManager.currentTaskRegisteredListener!!);
    }

    override fun run() {
        RushManager.timerTaskID = RushTimerTask(duration).runTaskTimer(MinuteRushPlugin.instance, 0, 20)
        RushManager.currentTaskRegisteredListener = StandOnBlockRushEvent()
        MinuteRushPlugin.instance.server.pluginManager.registerEvents(RushManager.currentTaskRegisteredListener as StandOnBlockRushEvent, MinuteRushPlugin.instance)
        SendTitle.sendAll(rushTitle)
    }
}