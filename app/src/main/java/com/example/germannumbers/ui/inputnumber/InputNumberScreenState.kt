package com.example.germannumbers.ui.inputnumber

data class InputNumberScreenState(
    val inputMatchesSpeech: Boolean = false,
    val text: String = "",
    var buttonColor: ButtonColor = ButtonColor.IDLE
)

enum class ButtonColor(state: Int) {
    IDLE(0),
    ERROR(1),
    SUCCESS(2),
}
