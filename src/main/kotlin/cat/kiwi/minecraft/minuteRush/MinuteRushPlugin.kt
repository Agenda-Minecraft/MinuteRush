package cat.kiwi.minecraft.minuteRush
import cat.kiwi.minecraft.minuteRush.border.WorldExpandTask
import cat.kiwi.minecraft.minuteRush.cmd.MinuteRushCmd
import cat.kiwi.minecraft.minuteRush.globalEvent.GlobalListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class MinuteRushPlugin:JavaPlugin() {
    companion object {
        lateinit var instance: MinuteRushPlugin
    }

    override fun onEnable() {
        instance = this
        logger.info("MinuteRush is enabled!")

        // Read Config File
        try {
            Config.readConfig()
        } catch (e: Exception) {
            logger.info("Cannot read configuration file!")
            Bukkit.getPluginManager().disablePlugin(this)
        }
        saveConfig()
        Lang.load()


        server.pluginManager.registerEvents(GlobalListener(),this)
        getCommand("minuterush")!!.setExecutor(MinuteRushCmd())

    }

    override fun onDisable() {
        logger.info("MinuteRush is disabled!")
    }
}