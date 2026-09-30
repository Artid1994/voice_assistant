package com.artid.voiceassistant.cognition

/**
 * Represents the core cognitive processing engine.
 * This interface defines the contract for the assistant's reasoning capabilities.
 * Implementations handle context processing, decision making, and action planning.
 */
interface CognitiveEngine {
    /**
     * Process the given input context and return a cognitive response.
     * @param context The input context to process
     * @return A cognitive response containing action plans or information
     */
    fun process(context: CognitiveContext): CognitiveResponse

    /**
     * Update the internal cognitive state based on new information.
     * @param stateUpdate The state update to apply
     */
    fun updateState(stateUpdate: CognitiveStateUpdate): Unit
}

/**
 * Represents the input context for cognitive processing.
 */
data class CognitiveContext(
    val input: String,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Represents the response from cognitive processing.
 */
data class CognitiveResponse(
    val action: CognitiveAction,
    val confidence: Float,
    val reasoning: String = ""
)

/**
 * Represents a cognitive action to be executed.
 */
sealed class CognitiveAction {
    data class ExecuteTask(val task: String, val parameters: Map<String, String> = emptyMap()) : CognitiveAction()
    data class QueryKnowledge(val query: String) : CognitiveAction()
    data object NoAction : CognitiveAction()
}

/**
 * Represents a state update for the cognitive engine.
 */
data class CognitiveStateUpdate(
    val stateType: StateType,
    val data: Any
)

enum class StateType {
    KNOWLEDGE_UPDATED,
    CONTEXT_CHANGED,
    MEMORY_COMMITTED
}
