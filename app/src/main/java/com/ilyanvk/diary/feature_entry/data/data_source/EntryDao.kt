package com.ilyanvk.diary.feature_entry.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyanvk.diary.feature_entry.domain.model.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry")
    fun getEntries(): Flow<List<Entry>>

    @Query("SELECT * FROM entry WHERE id = :id")
    suspend fun getEntryById(id: String): Entry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)
}
