package com.ilyanvk.diary.feature_entry.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyanvk.diary.feature_entry.domain.model.Entry

@Database(
    entities = [Entry::class], version = 1
)
abstract class EntryDatabase : RoomDatabase() {
    abstract val entryDao: EntryDao
}
