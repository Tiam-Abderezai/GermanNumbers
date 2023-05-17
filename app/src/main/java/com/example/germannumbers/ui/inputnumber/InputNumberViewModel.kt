package com.example.germannumbers.ui.inputnumber

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class InputNumberViewModel : ViewModel() {

    private val _state = mutableStateOf(InputNumberScreenState())
    val state: State<InputNumberScreenState> = _state
    private var textToSpeech: TextToSpeech? = null

    fun onTextFieldValueChange(text: String) {
        _state.value = state.value.copy(
            text = text
        )
    }

    fun textToSpeech(context: Context) {
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            println("bullshit $it")
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.GERMANY
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }
    }
}
