package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.data.RoleCustomImageStore
import com.example.ui.navigation.WerewolfApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RoleCustomImageStore.init(this)
        enableEdgeToEdge()
        setContent {
            WerewolfApp()
        }
    }
}
