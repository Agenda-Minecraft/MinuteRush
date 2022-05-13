package cat.kiwi.minecraft.minuteRush.dispaly

import org.bukkit.Bukkit

object SendTitle {

    fun sendAll(content: String, subContent: String? = null, fadeInt: Int = 10, stay: Int = 70, fadeOut: Int = 20) =
        Bukkit.getOnlinePlayers().forEach {
            it.sendTitle(content, subContent, fadeInt, stay, fadeOut)
        }
}
