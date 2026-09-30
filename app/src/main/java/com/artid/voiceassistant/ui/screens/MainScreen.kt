package com.artid.voiceassistant.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.artid.voiceassistant.voice.DefaultVoiceManager

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var voiceState by remember { mutableStateOf<com.artid.voiceassistant.voice.VoiceState>(com.artid.voiceassistant.voice.VoiceState.Idle) }
    val voiceManager = remember { DefaultVoiceManager(context) }

    DisposableEffect(voiceManager) {
        onDispose {
            voiceManager.stopListening()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Voice Assistant",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "AI Voice Assistant prototypes",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    voiceState = com.artid.voiceassistant.voice.VoiceState.Listening
                    voiceManager.startListening()
                },
                enabled = voiceState !is com.artid.voiceassistant.voice.VoiceState.Listening
            ) {
                Text("Start Listening")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (voiceState) {
                is com.artid.voiceassistant.voice.VoiceState.Listening -> {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Listening...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                is com.artid.voiceassistant.voice.VoiceState.Processing -> {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Processing...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                is com.artid.voiceassistant.voice.VoiceState.HasResult -> {
                    Text(
                        text = "Command received",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> { /* Idle - no additional UI */ }
            }
        }
    }
}
