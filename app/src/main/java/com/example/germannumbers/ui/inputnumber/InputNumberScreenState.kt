package com.example.germannumbers.ui.inputnumber

data class InputNumberScreenState(
    var inputMatchesSpeech: Boolean = false,
    var text: String = "",
    var buttonColor: ButtonColor = ButtonColor.IDLE,
    var scoreCount: Int = 0
)

enum class ButtonColor(state: Int) {
    IDLE(0),
    ERROR(1),
    SUCCESS(2),
}
