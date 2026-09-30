package com.artid.voiceassistant.tools

/**
 * Represents a tool that the assistant can use.
 * Tools are capabilities that can be invoked by the cognitive engine
 * to perform specific actions.
 */
interface Tool {
    /**
     * Get the unique identifier for this tool.
     */
    fun getToolId(): String

    /**
     * Get the display name for this tool.
     */
    fun getDisplayName(): String

    /**
     * Get a description of what this tool does.
     */
    fun getDescription(): String

    /**
     * Get the parameters this tool accepts.
     */
    fun getParameters(): Map<String, ParameterInfo>

    /**
     * Execute this tool with the given parameters.
     * @param parameters The parameters for execution
     * @return The execution result
     */
    fun execute(parameters: Map<String, String>): ToolResult
}

/**
 * Information about a tool parameter.
 */
data class ParameterInfo(
    val name: String,
    val description: String,
    val required: Boolean = true,
    val type: ParameterType = ParameterType.STRING
)

/**
 * Types of parameters a tool can accept.
 */
enum class ParameterType {
    STRING,
    INTEGER,
    FLOAT,
    BOOLEAN,
    LIST,
    OBJECT
}

/**
 * Result of tool execution.
 */
data class ToolResult(
    val success: Boolean,
    val output: String,
    val error: String? = null
)

/**
 * Registry for managing available tools.
 */
interface ToolRegistry {
    /**
     * Register a tool.
     * @param tool The tool to register
     */
    fun registerTool(tool: Tool): Unit

    /**
     * Unregister a tool.
     * @param toolId The ID of the tool to unregister
     */
    fun unregisterTool(toolId: String): Unit

    /**
     * Get a tool by its ID.
     * @param toolId The tool ID
     * @return The tool, or null if not found
     */
    fun getTool(toolId: String): Tool?

    /**
     * Get all registered tools.
     */
    fun getAllTools(): List<Tool>
}
