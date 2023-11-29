package com.ilyanvk.diary.feature_entry.domain.use_case

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.model.InvalidEntryException
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository

class AddEntryUseCase(
    private val entryRepository: EntryRepository
) {
    @Throws(InvalidEntryException::class)
    suspend operator fun invoke(entry: Entry) {
        if (entry.title.isBlank()) {
            throw InvalidEntryException("The title of the entry can't be empty.")
        }
        if (entry.content.isBlank()) {
            throw InvalidEntryException("The content of the entry can't be empty.")
        }
        entryRepository.addEntry(entry)
    }
}
