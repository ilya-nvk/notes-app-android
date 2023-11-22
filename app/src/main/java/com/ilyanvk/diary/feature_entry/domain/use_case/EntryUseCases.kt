package com.ilyanvk.diary.feature_entry.domain.use_case

data class EntryUseCases(
    val getEntriesUseCase: GetEntriesUseCase,
    val getEntryByIdUseCase: GetEntryByIdUseCase,
    val insertEntryUseCase: InsertEntryUseCase,
    val deleteEntryUseCase: DeleteEntryUseCase
)
