package com.example.germannumbers.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.germannumbers.R
import com.example.germannumbers.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item() {
            Card(
                Modifier
                    .width(255.dp)
                    .height(64.dp)
                    .combinedClickable(
                        enabled = true
                    ) {
                        navController.navigate(Screens.Number.route)
                    }
            ) {
                Image(
                    painterResource(
                        id = R.drawable.ic_numbers
                    ),
                    contentDescription = "",
                    alignment = Alignment.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                Modifier
                    .width(255.dp)
                    .height(64.dp)
                    .combinedClickable(
                        enabled = true
                    ) {
                        navController.navigate(Screens.Number.route)
                    }
            ) {
                Image(
                    painterResource(id = R.drawable.ic_time),
                    contentDescription = "",
                    alignment = Alignment.Center
                )
            }
        }
    }
}
