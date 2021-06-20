package br.com.canotes.framework

import android.content.Context
import br.com.canotes.framework.db.DataBaseService
import br.com.canotes.framework.db.NoteEntity
import br.com.core.data.Note
import br.com.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context):NoteDataSource {
    private val noteDao = DataBaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note)= noteDao.deleteNoteEntities(NoteEntity.fromNote(note))
}