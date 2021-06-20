package br.com.canotes.framework

import br.com.core.usecase.*

/*
    Aqui instanciamos todos os usecases, mais internos para usar em nossa arquiteura mais acima
 */
data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)
