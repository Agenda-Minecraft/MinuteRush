package cat.kiwi.minecraft.minuteRush.rush

import cat.kiwi.minecraft.minuteRush.Lang
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle

object RushTimerBossBar {
    private val bossBar = Bukkit.createBossBar(Lang.get("bossBar-title"), BarColor.BLUE, BarStyle.SOLID)
    fun display(time: Int, totalTime: Int) {
        Thread {
            bossBar.removeAll()
            Bukkit.getOnlinePlayers().forEach {
                bossBar.removePlayer(it)
            }
            var secRate = time.toDouble() / totalTime.toDouble()
            if (secRate > 1.0) secRate = 1.0
            if (secRate < 0.0) secRate = 0.0


            bossBar.progress = (secRate)
            bossBar.isVisible = true
            Bukkit.getOnlinePlayers().forEach {
                bossBar.addPlayer(it)
            }
        }.start()

    }
}