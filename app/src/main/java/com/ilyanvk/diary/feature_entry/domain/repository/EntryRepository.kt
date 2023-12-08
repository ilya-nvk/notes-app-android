package com.ilyanvk.diary.feature_entry.domain.repository

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    fun getEntries(): Flow<List<Entry>>

    suspend fun getEntryById(id: String): Entry?

    suspend fun addEntry(entry: Entry)

    suspend fun deleteEntry(entry: Entry)
}
