package com.example.echat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.echat.presentation.navigation.NavEntry
import com.example.echat.presentation.navigation.Navigation
import com.example.echat.ui.theme.EChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EChatTheme {
                NavEntry()
            }
        }
    }
}

