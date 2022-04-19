package cat.kiwi.minecraft.minuteRush.cmd

import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.border.WorldExpandTask
import cat.kiwi.minecraft.minuteRush.rush.RushManager
import cat.kiwi.minecraft.minuteRush.title.SendTitle
import net.md_5.bungee.api.ChatColor
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
                        SendTitle.sendAll("${ChatColor.GOLD}${ChatColor.BOLD}Game started!")
                        WorldExpandTask.expand()
                        RushManager.startRandomRush()
                    }
                    "stop" -> {
                        WorldExpandTask.stop()
                    }
                    else -> sender.sendMessage(helpInfo)
                }
            } catch (e: Exception) {
                sender.sendMessage(e.toString())
            }
            true
        }

}