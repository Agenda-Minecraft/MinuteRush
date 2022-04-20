package cat.kiwi.minecraft.minuteRush.border

import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

object WorldExpandTask {
    private var expandTaskID: BukkitTask? = null

    fun expand() {
        if (expandTaskID == null) {
            val taskID = ExpandTask().runTaskTimer(MinuteRushPlugin.instance, 10, 1)
            expandTaskID = taskID
        } else {
            MinuteRushPlugin.instance.logger.info("Cannot expand world, world does not exist")
            MinuteRushPlugin.instance.onDisable()
        }
    }

    fun stop() {
        expandTaskID!!.cancel()
        expandTaskID = null
    }
}

class ExpandTask : BukkitRunnable() {
    private val expand  = Expand()
    override fun run() {
        expand.expand(Config.expand_per_attempt)
    }
}