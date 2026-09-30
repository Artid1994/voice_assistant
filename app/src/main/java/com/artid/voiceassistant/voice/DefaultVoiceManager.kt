package com.artid.voiceassistant.voice

import android.content.Context

/**
 * Default (no-op) implementation of VoiceManager for the UI.
 * Real voice capture and recognition is deferred to Phase 4 (voice_pipeline).
 * This satisfies the UI contract so the app compiles and the screen renders.
 */
class DefaultVoiceManager(private val context: Context) : VoiceManager {

    private var state: VoiceState = VoiceState.Idle

    override fun startListening() {
        state = VoiceState.Listening
        // Real implementation in Phase 4
    }

    override fun stopListening() {
        if (state is VoiceState.Listening) {
            state = VoiceState.Idle
        }
    }

    override fun getState(): VoiceState = state
}
