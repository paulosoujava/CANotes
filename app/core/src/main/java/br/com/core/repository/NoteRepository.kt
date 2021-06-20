package br.com.core.repository

import br.com.core.data.Note

//teremos acesso ao repositório através desta classe NoteRepository
//temos um injeção de dependencia aqui [NoteDataSource]
class NoteRepository (private val dataSource: NoteDataSource){
    suspend fun addNote(note: Note) = dataSource.add(note)
    suspend fun getNote(id: Long) = dataSource.get(id)
    suspend fun getAllNote() = dataSource.getAll()
    suspend fun removeNote(note: Note) = dataSource.remove(note)
}