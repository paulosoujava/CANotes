package br.com.canotes.framework.di

import br.com.canotes.framework.UseCases
import br.com.core.repository.NoteRepository
import br.com.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUserCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}