package br.com.core.usecase

import br.com.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id:Long)= noteRepository.getNote(id)
}