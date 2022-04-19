package cat.kiwi.minecraft.minuteRush

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.bukkit.entity.Player
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption




object Lang {
    private lateinit var lang: JsonObject

    // Just get String
    fun get(key:String) = lang.get(key).asString.replace("&", "§")

    // Get String with player object's name String replace
    fun get(key:String, player: Player) = lang.get(key).asString.replace("&", "§").replace("%player%", player.name)


    fun load() {
        try {
            // load lang file
            val langFile = File("./plugins/MinuteRush/lang.json")
            val jsonString  = langFile.readText(Charsets.UTF_8)
            lang = JsonParser.parseString(jsonString).asJsonObject
            MinuteRushPlugin.instance.logger.info(lang.get("load-complete").asString)

        } catch (e: FileNotFoundException) {
            // copy stream to file
            val inputStream = this::class.java.getResourceAsStream("/lang.json")
            Files.copy(inputStream, Paths.get("plugins/MinuteRush/lang.json"), StandardCopyOption.REPLACE_EXISTING)
            load()
        } catch (e: Exception) {
            e.printStackTrace()
            MinuteRushPlugin.instance.logger.info("Cannot load lang.json")
            MinuteRushPlugin.instance.onDisable()
        }
    }

}