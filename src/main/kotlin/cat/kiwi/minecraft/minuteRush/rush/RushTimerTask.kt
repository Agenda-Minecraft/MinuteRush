package cat.kiwi.minecraft.minuteRush.rush

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.scheduler.BukkitRunnable

class RushTimerTask(var time: Int) : BukkitRunnable() {
    private val totalTime = time
    override fun run() {
        time -= 20
        if (time <= 0) {
            RushManager.currentRush?.cancel()
        }
        RushTimerBossBar.display(time,totalTime)
    }

}