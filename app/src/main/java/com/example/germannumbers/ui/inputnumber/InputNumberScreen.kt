package com.example.germannumbers.ui.inputnumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.germannumbers.R
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun InputNumberScreen(
    viewModel: InputNumberViewModel = viewModel()
) {
    val stateTextSpeak = viewModel.stateTextSpeech.value
    val stateTextInput = viewModel.stateTextInput.value
    val stateInputMatchesSpeech = viewModel.stateInputMatchesSpeech.value
    val stateButtonColor = viewModel.stateButtonColor.value

    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val randomNumber = Random.nextInt(0, 1000).toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(320.dp))
        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = stateTextInput.text,
            onValueChange = {
                viewModel.onTextFieldValueChange(it)
            }
        )
        LaunchedEffect(Unit) {
            println("rando numb $randomNumber")
//            viewModel.onTextFieldValueChange(randomNumber)
            viewModel.textToSpeech(context, randomNumber)
            focusRequester.requestFocus()
        }
        Row {
            // Repeat the number to user
            Button(
                onClick = {
                    viewModel.textToSpeech(context, stateTextSpeak.text)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_speak),
                    contentDescription = "",
                    Modifier.size(32.dp)
                )
            }
            // Verify if 'stateTextInput' matches number 'stateTextSpeech'
            val isAnswerCorrect = stateInputMatchesSpeech.inputMatchesSpeech
            val isTextFieldEmpty = stateTextInput.text.isNotEmpty()
            println("isAnswerCorrect $isAnswerCorrect")
            println("stateButtonColor ${stateButtonColor.buttonColor.ordinal}")
            Button(
                onClick = {
                    viewModel.validateInputMatchesSpeech(stateTextInput.text)
                },
                enabled = isTextFieldEmpty,
                colors = ButtonDefaults.buttonColors(
                    when (stateButtonColor.buttonColor.ordinal) {
                        1 -> Color.Red
                        2 -> Color.Green
                        else -> {
                            Color.Gray
                        }
                    }
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "",
                    Modifier.size(32.dp)
                )
            }
        }
    }
}
