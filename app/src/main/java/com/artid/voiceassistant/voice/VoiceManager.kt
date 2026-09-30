package com.artid.voiceassistant.voice

/**
 * Represents the current state of voice processing.
 */
sealed class VoiceState {
    data object Idle : VoiceState()
    data object Listening : VoiceState()
    data object Processing : VoiceState()
    data class HasResult(val text: String) : VoiceState()
    data class Error(val message: String) : VoiceState()
}

/**
 * Voice manager interface for handling voice input and output.
 * This defines the contract for voice recognition and synthesis.
 * Actual implementation is deferred - this skeleton provides the interface.
 */
interface VoiceManager {
    /**
     * Start listening for voice input.
     */
    fun startListening(): Unit

    /**
     * Stop the current listening session.
     */
    fun stopListening(): Unit

    /**
     * Get the current voice state.
     */
    fun getState(): VoiceState
}

/**
 * Text-to-speech interface for voice output.
 */
interface TextToSpeech {
    /**
     * Speak the given text.
     * @param text The text to speak
     */
    fun speak(text: String): Unit

    /**
     * Stop any ongoing speech.
     */
    fun stop(): Unit

    /**
     * Check if speech is currently playing.
     */
    fun isSpeaking(): Boolean
}
