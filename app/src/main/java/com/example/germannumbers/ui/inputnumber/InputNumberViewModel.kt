package com.example.germannumbers.ui.inputnumber

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class InputNumberViewModel : ViewModel() {

    private val _stateTextOutput = mutableStateOf(InputNumberScreenState())
    val stateTextOutput: State<InputNumberScreenState> = _stateTextOutput

    private val _stateTextInput = mutableStateOf(InputNumberScreenState())
    val stateTextInput: State<InputNumberScreenState> = _stateTextInput

    private var textToSpeech: TextToSpeech? = null

    fun onTextFieldValueChange(text: String) {
        _stateTextInput.value = stateTextInput.value.copy(
            text = text
        )
    }

    fun textToSpeech(context: Context, number: String) {
        _stateTextOutput.value = stateTextOutput.value.copy(
            isButtonEnabled = false,
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
                        _stateTextOutput.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _stateTextInput.value = stateTextInput.value.copy(
                isButtonEnabled = true
            )
        }
    }
}
