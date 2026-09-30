package com.artid.voiceassistant.agent

/**
 * Represents an agent capability within the assistant.
 * Agents are specialized components that handle specific types of tasks.
 */
interface Agent {
    /**
     * Get the unique identifier for this agent.
     */
    fun getAgentId(): String

    /**
     * Get the display name for this agent.
     */
    fun getDisplayName(): String

    /**
     * Check if this agent can handle the given request.
     * @param request The request to check
     * @return True if this agent can handle the request
     */
    fun canHandle(request: AgentRequest): Boolean

    /**
     * Execute the given request.
     * @param request The request to execute
     * @return The execution result
     */
    fun execute(request: AgentRequest): AgentResult
}

/**
 * Represents a request to an agent.
 */
data class AgentRequest(
    val intent: String,
    val parameters: Map<String, String> = emptyMap(),
    val context: Map<String, String> = emptyMap()
)

/**
 * Represents the result of agent execution.
 */
data class AgentResult(
    val success: Boolean,
    val output: String,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Manager for coordinating multiple agents.
 */
interface AgentManager {
    /**
     * Register an agent for use.
     * @param agent The agent to register
     */
    fun registerAgent(agent: Agent): Unit

    /**
     * Unregister an agent.
     * @param agentId The ID of the agent to unregister
     */
    fun unregisterAgent(agentId: String): Unit

    /**
     * Find the best agent for a given request.
     * @param request The request to find an agent for
     * @return The agent that can handle the request, or null
     */
    fun findAgentFor(request: AgentRequest): Agent?

    /**
     * Execute a request using the appropriate agent.
     * @param request The request to execute
     * @return The execution result
     */
    fun execute(request: AgentRequest): AgentResult
}
