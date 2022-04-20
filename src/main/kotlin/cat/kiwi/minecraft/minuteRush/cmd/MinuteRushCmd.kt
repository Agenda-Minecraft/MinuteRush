package cat.kiwi.minecraft.minuteRush.cmd

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.border.WorldExpandTask
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class MinuteRushCmd : CommandExecutor {
    val helpInfo = "Nothing here."

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>) =

        with(MinuteRushPlugin.instance) {
            if (args.isEmpty()) {
                sender.sendMessage(helpInfo)
            }
            try {
                when (args[0]) {
                    "start" -> {
                        RushManager.startRush()
                    }
                    "stop" -> {
                        RushManager.stopRush()
                    }
                    else -> sender.sendMessage(helpInfo)
                }
            } catch (e: Exception) {
                sender.sendMessage(e.toString())
            }
            true
        }

}