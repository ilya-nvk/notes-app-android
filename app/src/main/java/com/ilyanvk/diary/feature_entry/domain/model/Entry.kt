package com.ilyanvk.diary.feature_entry.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    val title: String,
    val content: String,
    val timeCreated: Long,
    val timeModified: Long,
    @PrimaryKey val id: String
)
