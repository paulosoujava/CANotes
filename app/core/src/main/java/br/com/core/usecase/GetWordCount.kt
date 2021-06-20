package br.com.core.usecase

import br.com.core.data.Note

class GetWordCount  {
    operator fun invoke(note: Note) = getCount(note.content)+ getCount(note.content)

    private fun getCount(str: String)=
        str.split( " ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()

}




