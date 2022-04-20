package cat.kiwi.minecraft.minuteRush.rush.mission

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.rush.RushTimerTask
import cat.kiwi.minecraft.minuteRush.rush.rushEvents.StandOnBlockRushEvent
import cat.kiwi.minecraft.minuteRush.dispaly.SendTitle
import org.bukkit.Material
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class StandOnBlockRushContext(private val rushTitle: String, private val duration: Int, private val block: String) :
    BukkitRunnable() {
    val material get() = Material.getMaterial(block)
    private var timerTaskID: BukkitTask? = null
        set(value) = run { field = value }

    override fun cancel() {
        RushManager.currentRushTask!!.cancel()
        RushManager.currentRushTask = null
        RushManager.currentRush = null
        timerTaskID!!.cancel()

        PlayerMoveEvent.getHandlerList().unregister(RushManager.currentTaskRegisteredListener!!)
    }

    override fun run() {
        timerTaskID = RushTimerTask(duration).runTaskTimer(MinuteRushPlugin.instance, 0, 20)
        RushManager.currentTaskRegisteredListener = StandOnBlockRushEvent()
        MinuteRushPlugin.instance.server.pluginManager.registerEvents(
            RushManager.currentTaskRegisteredListener as StandOnBlockRushEvent,
            MinuteRushPlugin.instance
        )
        SendTitle.sendAll(rushTitle)
    }
}