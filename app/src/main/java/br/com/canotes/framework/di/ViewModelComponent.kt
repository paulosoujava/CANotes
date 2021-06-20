package br.com.canotes.framework.di

import br.com.canotes.framework.ListViewModel
import br.com.canotes.framework.NoteViewModel
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    RepositoryModule::class,
    UseCasesModule::class
])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}