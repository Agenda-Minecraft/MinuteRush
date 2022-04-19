package cat.kiwi.minecraft.minuteRush.rush

import cat.kiwi.minecraft.minuteRush.Lang
import cat.kiwi.minecraft.minuteRush.MinuteRushPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Score
import java.util.*


object RushScoreBoard {
    val playerMapScore = Collections.synchronizedMap(HashMap<String, Int>())
    val manager = Bukkit.getScoreboardManager()
    val board = manager!!.newScoreboard
    val objective = board.registerNewObjective("test", "dummy", Lang.get("scoreboard-title"))

    init {
        objective.displaySlot = DisplaySlot.SIDEBAR
    }

    fun flush() {
        Bukkit.getOnlinePlayers().forEach {
            playerMapScore.map { m ->
                objective.getScore(m.key).score = m.value
            }
            it.scoreboard = board
        }
    }

    fun inc(e: String) {
        playerMapScore[e] = playerMapScore[e]?.plus(1)
        flush()
    }
}