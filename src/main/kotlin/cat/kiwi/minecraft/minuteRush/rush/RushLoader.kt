package cat.kiwi.minecraft.minuteRush.rush

import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import cat.kiwi.minecraft.minuteRush.dispaly.SendTitle
import cat.kiwi.minecraft.minuteRush.rush.mission.SendMessageRushContext
import cat.kiwi.minecraft.minuteRush.rush.mission.StandOnBlockRushContext
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

object RushLoader {
    fun JsonElement.coloredString() = this.asString.replace("&", "§")

    fun load() {
        try {
            appendRush()
            SendTitle.sendAll(Lang.get("rush.start"))
            MinuteRushPlugin.instance.logger.info(Lang.get("rush.loaded"))
        } catch (e: FileNotFoundException) {
            // copy stream to file
            val inputStream = this::class.java.getResourceAsStream("/rush.json")
            Files.copy(inputStream, Paths.get("plugins/MinuteRush/rush.json"), StandardCopyOption.REPLACE_EXISTING)
            load()
        } catch (e: Exception) {
            e.printStackTrace()
            MinuteRushPlugin.instance.logger.info("Cannot load rush.json")
            MinuteRushPlugin.instance.onDisable()
        }
    }

    private fun appendRush() {
        // load rush file
        val rushFile = File("./plugins/MinuteRush/rush.json")
        val jsonString = rushFile.readText(Charsets.UTF_8)
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        val rush = jsonObject.get("rush").asJsonArray

        // append rush
        for (r in rush) {
            val mission = r.asJsonObject
            when (mission["type"].asString) {
                "SendMessageRush" -> {
                    SendMessageRushContext(
                        mission["title"].coloredString(),
                        mission["duration"].asInt
                    ).let { RushManager.rushMissions.add(it) }
                }
                "StandOnBlockRush" -> {
                    StandOnBlockRushContext(
                        mission["title"].coloredString(),
                        mission["duration"].asInt,
                        mission["material"].asString
                    ).let { RushManager.rushMissions.add(it) }
                }
            }
        }
    }
}