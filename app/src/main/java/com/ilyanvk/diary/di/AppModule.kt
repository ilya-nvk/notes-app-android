package com.ilyanvk.diary.di

import android.app.Application
import androidx.room.Room
import com.ilyanvk.diary.feature_entry.data.data_source.EntryDatabase
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepositoryImpl
import com.ilyanvk.diary.feature_entry.domain.use_case.DeleteEntryUseCase
import com.ilyanvk.diary.feature_entry.domain.use_case.EntryUseCases
import com.ilyanvk.diary.feature_entry.domain.use_case.GetEntriesUseCase
import com.ilyanvk.diary.feature_entry.domain.use_case.GetEntryByIdUseCase
import com.ilyanvk.diary.feature_entry.domain.use_case.AddEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideEntryDatabase(app: Application): EntryDatabase {
        return Room.databaseBuilder(
            app, EntryDatabase::class.java, EntryDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEntryRepository(database: EntryDatabase): EntryRepository {
        return EntryRepositoryImpl(database.entryDao)
    }

    @Provides
    @Singleton
    fun provideEntryUseCases(repository: EntryRepository): EntryUseCases {
        return EntryUseCases(
            getEntriesUseCase = GetEntriesUseCase(repository),
            getEntryByIdUseCase = GetEntryByIdUseCase(repository),
            addEntryUseCase = AddEntryUseCase(repository),
            deleteEntryUseCase = DeleteEntryUseCase(repository)
        )
    }
}