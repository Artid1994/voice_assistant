package com.artid.voiceassistant.data

/**
 * Repository interface for data access.
 * This defines the contract for data storage and retrieval.
 * Implementations can use Room, DataStore, or other storage mechanisms.
 */
interface DataRepository {
    /**
     * Save data to the repository.
     * @param key The key to store the data under
     * @param value The data to store
     */
    fun save(key: String, value: String): Unit

    /**
     * Retrieve data from the repository.
     * @param key The key to retrieve
     * @return The data value, or null if not found
     */
    fun get(key: String): String?

    /**
     * Delete data from the repository.
     * @param key The key to delete
     */
    fun delete(key: String): Unit

    /**
     * Check if a key exists in the repository.
     * @param key The key to check
     * @return True if the key exists
     */
    fun exists(key: String): Boolean

    /**
     * Get all keys in the repository.
     * @return List of all keys
     */
    fun getAllKeys(): List<String>
}

/**
 * Exception thrown when data operations fail.
 */
class DataException(
    message: String,
    val cause: Throwable? = null
) : Exception(message, cause)
