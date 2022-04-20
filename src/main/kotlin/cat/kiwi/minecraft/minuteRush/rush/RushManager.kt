package cat.kiwi.minecraft.minuteRush.rush

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.rush.mission.SendMessageRush
import cat.kiwi.minecraft.minuteRush.rush.mission.StandOnBlockRush
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import net.md_5.bungee.api.ChatColor
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class RushManager {
    companion object {
        private val rushMissions: ArrayList<BukkitRunnable> = ArrayList()
        init {
            val material = "WORKBENCH"
            rushMissions.add(SendMessageRush("发送消息", 1500))
            rushMissions.add(StandOnBlockRush("站在石头上", 1500, material))
            rushMissions.add(SendMessageRush("发送消息", 1500))
            rushMissions.add(StandOnBlockRush("站在石头上", 1500, material))
            rushMissions.add(SendMessageRush("发送消息", 1500))
            rushMissions.add(StandOnBlockRush("站在石头上", 1500, material))
        }
        var taskLock = false
        var currentRush: BukkitRunnable? = null
        var currentRushTask: BukkitTask? = null
        var currentTaskRegisteredListener: Listener? = null

        fun startRandomRush() {
            Thread {
                while (true) {
                    Thread.sleep(100)
                    if (taskLock) {
                        continue
                    }
                    if (currentRush == null) {
                        if (rushMissions.isEmpty()) {
                            SendTitle.sendAll("${ChatColor.GOLD}${ChatColor.BOLD}Game ended!")
                            break
                        }
                        val rushID = (0 until rushMissions.size).random()
                        val rush = rushMissions[rushID]
                        rushMissions.removeAt(rushID)

                        MinuteRushPlugin.instance.logger.info("Task $rush running")
                        currentRush = rush
                        currentRushTask = rush.runTask(MinuteRushPlugin.instance)
                    }
                }
            }.start()
        }
    }
}
