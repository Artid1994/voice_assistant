package com.artid.voiceassistant.llm

/**
 * Represents an LLM (Large Language Model) provider.
 * This interface defines the contract for interacting with language models.
 * Implementations can support local models, remote APIs, or mock providers.
 *
 * The key principle: LLM is an adapter/provider, NOT the identity of the assistant.
 * The cognitive core and memory are separate from the LLM layer.
 */
interface LlmProvider {
    /**
     * Generate a response from the language model.
     * @param prompt The input prompt
     * @param options Configuration options for generation
     * @return The generated response
     */
    fun generate(prompt: String, options: GenerationOptions = GenerationOptions()): LlmResponse

    /**
     * Check if the provider is currently available.
     * @return True if the provider can process requests
     */
    fun isAvailable(): Boolean

    /**
     * Get the name/identifier of this LLM provider.
     * @return Provider identifier
     */
    fun getProviderName(): String
}

/**
 * Represents the response from an LLM.
 */
data class LlmResponse(
    val content: String,
    val provider: String,
    val tokensUsed: Int = 0,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Configuration options for LLM generation.
 */
data class GenerationOptions(
    val maxTokens: Int = 1024,
    val temperature: Float = 0.7f,
    val topP: Float = 1.0f,
    val systemPrompt: String = "",
    val stopSequences: List<String> = emptyList()
)

/**
 * Exception thrown when LLM provider encounters an error.
 */
class LlmProviderException(
    message: String,
    val cause: Throwable? = null
) : Exception(message, cause)
