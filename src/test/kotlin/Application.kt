import cat.kiwi.minecraft.minuteRush.rush.mission.SendMessageRushContext
import cat.kiwi.minecraft.minuteRush.rush.mission.StandOnBlockRushContext
import com.google.gson.JsonParser

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val j = "{\n" +
                    "  \"rush\": [\n" +
                    "    {\n" +
                    "      \"type\": \"SendMessageRush\",\n" +
                    "      \"title\": \"发送任意消息\",\n" +
                    "      \"duration\": 300\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"type\": \"StandOnBlockRush\",\n" +
                    "      \"title\": \"站在工作台上\",\n" +
                    "      \"duration\": 1000,\n" +
                    "      \"material\": \"WORKBENCH\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"
            val jsonObject = JsonParser.parseString(j).asJsonObject
            val rushs = jsonObject.get("rush").asJsonArray
            for (i in rushs) {
                val r = i.asJsonObject
                when (r["type"].asString) {
                    "SendMessageRush" -> {
                        val rush = SendMessageRushContext(r["title"].asString, r["duration"].asInt)
                        rush.run()
                    }
                    "StandOnBlockRush" -> {
                        val rush = StandOnBlockRushContext(r["title"].asString, r["duration"].asInt, r["material"].asString)
                        rush.run()
                    }
                }
            }
        }
    }
}