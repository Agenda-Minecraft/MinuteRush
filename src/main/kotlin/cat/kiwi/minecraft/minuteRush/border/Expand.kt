package cat.kiwi.minecraft.minuteRush.border

import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import org.bukkit.Bukkit

class Expand {
    private val world = Bukkit.getWorld(Config.world)!!
    private val worldBorder = world.worldBorder
    private var baseSize = Config.base_range

    init {
        worldBorder.setCenter(Config.center_x, Config.center_y)
    }

    fun expand(range: Double) {
        worldBorder.size = baseSize
        baseSize += range

        if (baseSize.toInt() % 10 == 0) {

            MinuteRushPlugin.instance.logger.info("Expanded to $baseSize")
        }
    }
}