package com.ilyanvk.diary.feature_entry.domain.use_case

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository

class InsertEntryUseCase(
    private val entryRepository: EntryRepository
) {
    suspend operator fun invoke(entry: Entry) {
        entryRepository.insertEntry(entry)
    }
}
