package com.example.germannumbers.ui.inputnumber

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class InputNumberViewModel : ViewModel() {

    private var _stateTextSpeech = mutableStateOf(InputNumberScreenState())
    val stateTextSpeech: State<InputNumberScreenState> = _stateTextSpeech

    private var _stateTextInput = mutableStateOf(InputNumberScreenState())
    val stateTextInput: State<InputNumberScreenState> = _stateTextInput

    private var _stateInputMatchesSpeech = mutableStateOf(InputNumberScreenState())
    val stateInputMatchesSpeech: State<InputNumberScreenState> = _stateInputMatchesSpeech

    private var _stateButtonColor = mutableStateOf(InputNumberScreenState())
    val stateButtonColor: State<InputNumberScreenState> = _stateButtonColor

    private var _stateScoreCount = mutableStateOf(InputNumberScreenState())
    val stateScoreCount: State<InputNumberScreenState> = _stateScoreCount

    private var textToSpeech: TextToSpeech? = null

    fun onTextFieldValueChange(text: String) {
        _stateTextInput.value = stateTextInput.value.copy(
            text = text
        )
        _stateButtonColor.value = stateButtonColor.value.copy(buttonColor = ButtonColor.IDLE)
    }

    fun textToSpeech(context: Context, number: String) {
        _stateTextSpeech.value = stateTextSpeech.value.copy(
//            isButtonEnabled = false,
            text = number
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.GERMANY
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _stateTextSpeech.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _stateTextInput.value = stateTextInput.value.copy(
//                isButtonEnabled = true
            )
        }
    }

    fun validateInputMatchesSpeech(number: String) {
        println("stateTextInput: ${stateTextSpeech.value.text} vs $number")
        println("scoreCount: ${stateScoreCount.value.scoreCount}")
        val isAnswerCorrect = stateTextSpeech.value.text == number
        _stateInputMatchesSpeech.value = stateInputMatchesSpeech.value.copy(inputMatchesSpeech = isAnswerCorrect)
        if (isAnswerCorrect) {
            _stateButtonColor.value = stateButtonColor.value.copy(buttonColor = ButtonColor.SUCCESS)
//            _stateScoreCount.value = stateScoreCount.value.copy(scoreCount = stateScoreCount.value.scoreCount++)
            _stateScoreCount.value.scoreCount++
        } else {
            _stateButtonColor.value = stateButtonColor.value.copy(buttonColor = ButtonColor.ERROR)
        }
    }
}
