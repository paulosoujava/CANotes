package br.com.core.data

import br.com.core.usecase.GetWordCount

//pojo project not contain functionality
// classe POJO  (Plan Old Java Object) não contain funcionalidades

class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0,
    var wordCount: Int = 0
)