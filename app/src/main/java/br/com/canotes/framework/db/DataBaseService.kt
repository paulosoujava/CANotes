package br.com.canotes.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    NoteEntity::class
], version = 1)
abstract class DataBaseService: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "note.db"
        private var instance: DataBaseService? = null

        private fun create(context: Context): DataBaseService =
            Room.databaseBuilder(context, DataBaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        //singleton
        fun getInstance(context: Context): DataBaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun noteDao(): NoteDao
}

/*
Objetos companheiros [companion object]

Se você precisa escrever uma função que pode ser chamada sem ter uma instância de classe,
mas precisa de acesso aos componentes internos de uma classe (por exemplo, um método de fábrica),
 você pode escrevê-la como um membro de uma declaração de objeto dentro dessa classe.

Ainda mais especificamente, se você declarar um objeto companheiro dentro de sua classe, poderá acessar seus membros usando apenas o nome da classe como um qualificador.
 */

/*
also é como apply:
 leva o receptor, executa alguma ação nele e retorna aquele receptor.
 A diferença é que no bloco dentro de aplicar o receptor está disponível como este,
 enquanto no bloco dentro também está disponível como ele (e você pode dar outro nome se quiser).
  Isso é útil quando você não quer obscurecer isso do escopo externo:
  fun Block.copy() = Block().also {
    it.content = this.content
}
 */