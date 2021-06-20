package br.com.canotes.framework.di

import android.app.Application
import br.com.canotes.framework.RoomNoteDataSource
import br.com.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}