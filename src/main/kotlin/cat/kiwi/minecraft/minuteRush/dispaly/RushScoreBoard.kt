package cat.kiwi.minecraft.minuteRush.dispaly

import cat.kiwi.minecraft.minuteRush.Lang
import org.bukkit.Bukkit
import org.bukkit.scoreboard.DisplaySlot
import java.util.*


object RushScoreBoard {
    val playerMapScore: MutableMap<String, Int> = Collections.synchronizedMap(HashMap<String, Int>())
    private val manager = Bukkit.getScoreboardManager()
    private val board = manager!!.newScoreboard
    private val objective = board.registerNewObjective("test", "dummy", Lang.get("scoreboard.title"))

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
        playerMapScore[e] = playerMapScore[e]!!.plus(1)
        flush()
    }
}