package com.ilyanvk.diary.feature_entry.domain.repository

import com.ilyanvk.diary.feature_entry.data.data_source.EntryDao
import com.ilyanvk.diary.feature_entry.domain.model.Entry
import kotlinx.coroutines.flow.Flow

class EntryRepositoryImpl(
    private val entryDao: EntryDao
) : EntryRepository {
    override fun getEntries(): Flow<List<Entry>> {
        return entryDao.getEntries()
    }

    override suspend fun getEntryById(id: String): Entry? {
        return entryDao.getEntryById(id)
    }

    override suspend fun insertEntry(entry: Entry) {
        entryDao.insertEntry(entry)
    }

    override suspend fun deleteEntry(entry: Entry) {
        entryDao.deleteEntry(entry)
    }
}