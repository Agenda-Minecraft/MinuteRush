package cat.kiwi.minecraft.minuteRush.utils

import cat.kiwi.minecraft.minuteRush.Config
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin

object Logger {
    fun debug(msg: String) {
        if (Config.debug) {
            MinuteRushPlugin.instance.logger.info("[DEBUG] $msg")
        }
    }

    fun info(msg: String) {
        MinuteRushPlugin.instance.logger.info(msg)
    }

    fun warn(msg: String) {
        MinuteRushPlugin.instance.logger.warning(msg)
    }

    fun error(msg: String) {
        MinuteRushPlugin.instance.logger.severe(msg)
    }
}