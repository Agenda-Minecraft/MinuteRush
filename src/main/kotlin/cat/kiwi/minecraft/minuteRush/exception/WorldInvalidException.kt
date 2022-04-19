package cat.kiwi.minecraft.minuteRush.exception

class WorldInvalidException:Exception() {
    override val message: String
        get() = "World Invalided"
}