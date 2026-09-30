package com.artid.voiceassistant.memory

/**
 * Represents a memory store for the assistant.
 * This interface defines the contract for storing and retrieving information.
 * Implementations can range from simple in-memory stores to persistent databases.
 */
interface MemoryStore {
    /**
     * Store a memory entry.
     * @param entry The memory entry to store
     */
    fun store(entry: MemoryEntry): Unit

    /**
     * Retrieve memories matching the given query.
     * @param query The query to match memories against
     * @param limit Maximum number of results to return
     * @return List of matching memory entries
     */
    fun retrieve(query: MemoryQuery, limit: Int = 10): List<MemoryEntry>

    /**
     * Delete a memory entry by its ID.
     * @param id The ID of the memory entry to delete
     */
    fun delete(id: String): Unit

    /**
     * Clear all stored memories.
     */
    fun clear(): Unit
}

/**
 * Represents a single memory entry.
 */
data class MemoryEntry(
    val id: String,
    val content: String,
    val timestamp: Long,
    val tags: Set<String> = emptySet(),
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Represents a query for memory retrieval.
 */
data class MemoryQuery(
    val text: String,
    val tags: Set<String> = emptySet(),
    val timeRange: TimeRange? = null
)

/**
 * Represents a time range for memory queries.
 */
data class TimeRange(
    val start: Long,
    val end: Long
)
