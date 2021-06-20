package br.com.core.repository

import br.com.core.data.Note

// não importa de onde vem esta informação desde que
//respeite a interface

interface NoteDataSource {
    suspend fun add(note: Note)
    suspend fun get(id: Long): Note?
    suspend fun getAll(): List<Note>
    suspend fun remove(note: Note)
}