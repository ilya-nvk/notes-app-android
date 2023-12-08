package com.ilyanvk.diary.feature_entry.domain.use_case

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.model.InvalidEntryException
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository
import java.util.UUID

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
        if (entry.id.isBlank()) {
            entryRepository.addEntry(entry.copy(id = UUID.randomUUID().toString()))
        } else {
            entryRepository.addEntry(entry)
        }
    }
}
