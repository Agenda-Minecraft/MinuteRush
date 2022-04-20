package cat.kiwi.minecraft.minuteRush.border

import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

object WorldExpandTask {
    private var expandTask: BukkitTask? = null

    fun expand() {
        if (expandTask == null) {
            val task = ExpandTask().runTaskTimer(MinuteRushPlugin.instance, 10, 1)
            expandTask = task
        } else {
            MinuteRushPlugin.instance.logger.info("Cannot expand world, world does not exist")
            MinuteRushPlugin.instance.onDisable()
        }
    }

    fun stop() {
        expandTask?.cancel()
        expandTask = null
    }
}

class ExpandTask : BukkitRunnable() {
    private val expand  = Expand()
    override fun run() {
        expand.expand(Config.expand_per_attempt)
    }
}