package br.com.core.usecase

import br.com.core.data.Note
import br.com.core.repository.NoteRepository

//em usecases usamos um principio do SOLID que é o single responsability
// cada classe em usecase tera um Responsabilidade unica
// auqi também temos uma injeçao de dependencia [NoteRepository]

class AddNote( private val noteRepository: NoteRepository){

    //operator = marca uma função como sobrecarregando um operador ou implementando uma convenção
    //E queremos um operador para que possamos simplesmente invocar essa classe como um operador em um objeto.
    //por isto usamos esta convenção suspend operator fun invoke
  suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}

/*
A conveção invoke permite chamar objetos de tipos personalizados
como funções.Sabemos que objetos de tipos funcão, podem ser chamados como
funções, com a conveção invoke podemos definir nossos próprios objetos aceitando
a mesma sintaxe
 */