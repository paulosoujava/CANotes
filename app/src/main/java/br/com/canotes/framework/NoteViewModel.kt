package br.com.canotes.framework


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.canotes.framework.di.ApplicationModule
import br.com.canotes.framework.di.DaggerViewModelComponent
import br.com.core.data.Note
import br.com.core.repository.NoteRepository
import br.com.core.usecase.AddNote
import br.com.core.usecase.GetAllNotes
import br.com.core.usecase.GetNote
import br.com.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


// Aqui o contexto é requirido, como ele precisa vir de algum lugar
// ele virá do view model
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val repository = NoteRepository(RoomNoteDataSource(application))

    @Inject
    lateinit var usecases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(
                ApplicationModule(
                    getApplication()
                )
            )
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            usecases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = usecases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            usecases.removeNote(note)
            saved.postValue(true)
        }
    }
}