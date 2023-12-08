package com.ilyanvk.diary.feature_entry.domain.use_case

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository

class GetEntryByIdUseCase(
    private val entryRepository: EntryRepository
) {
    suspend operator fun invoke(id: String): Entry? {
        return entryRepository.getEntryById(id)
    }
}
