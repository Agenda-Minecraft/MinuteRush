package cat.kiwi.minecraft.minuteRush

object Config {
    var version = 1
    var world = "world"
    var center_x = 0.0
    var center_y = 0.0
    var base_range = 50.0
    var max_range = 500.0
    var expand_period_tick = 1
    var expand_per_attempt = 0.1
    var delay_per_task = 2000L
    var game_start_delay = 2000L
    var startPlayer = 10
    var debug = true
    fun readConfig() = with(MinuteRushPlugin.instance.config) {
        options().copyDefaults(true)
        debug = getBoolean("debug")
        version = getInt("1")
        world = getString("world")!!
        center_x = getDouble("center-x")
        center_y = getDouble("center-y")
        base_range = getDouble("base-range")
        max_range = getDouble("max-range")
        expand_period_tick = getInt("expand-period-tick")
        expand_per_attempt = getDouble("expand-per-attempt")
        delay_per_task = getLong("delay-per-task")
        game_start_delay = getLong("game-start-delay")
        startPlayer = getInt("start-player")
    }
}