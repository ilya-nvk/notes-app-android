package com.ilyanvk.diary.feature_entry.domain.use_case

data class EntryUseCases(
    val getEntriesUseCase: GetEntriesUseCase,
    val getEntryByIdUseCase: GetEntryByIdUseCase,
    val addEntryUseCase: AddEntryUseCase,
    val deleteEntryUseCase: DeleteEntryUseCase
)
