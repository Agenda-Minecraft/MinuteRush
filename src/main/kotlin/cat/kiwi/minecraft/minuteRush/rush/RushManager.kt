package cat.kiwi.minecraft.minuteRush.rush

import cat.kiwi.minecraft.metcd.MEtcd
import cat.kiwi.minecraft.metcd.model.GameStatus
import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.border.WorldExpandTask
import cat.kiwi.minecraft.minuteRush.dispaly.SendTitle
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.*
import kotlin.collections.ArrayList

class RushManager {
    companion object {
        val rushMissions: MutableList<BukkitRunnable> = Collections.synchronizedList(ArrayList<BukkitRunnable>())

        var taskLock = false
        var currentRush: BukkitRunnable? = null
        var currentRushTask: BukkitTask? = null
        var currentTaskRegisteredListener: Listener? = null

        fun startRush() {
            MEtcd.setGameStatus(GameStatus.RUNNING)
            // Synchronously
            WorldExpandTask.expand()
            // Asynchronously
            Thread {
                RushLoader.load()
                Thread.sleep(Config.game_start_delay)
                while (true) {
                    Thread.sleep(100)
                    if (taskLock) {
                        continue
                    }
                    if (currentRush == null) {
                        if (rushMissions.isEmpty()) break
                        val rushID = (0 until rushMissions.size).random()
                        val rush = rushMissions[rushID]
                        rushMissions.removeAt(rushID)

                        MinuteRushPlugin.instance.logger.info("Task $rush running")
                        currentRush = rush
                        currentRushTask = rush.runTask(MinuteRushPlugin.instance)
                    }
                }
                stopRush()
            }.start()
        }

        fun stopRush() {
            MEtcd.setGameStatus(GameStatus.ENDING)
            SendTitle.sendAll(Lang.get("rush.end"))
            WorldExpandTask.stop()
            currentRush?.cancel()
            currentRush = null
            rushMissions.removeAll { true }
        }
    }
}
