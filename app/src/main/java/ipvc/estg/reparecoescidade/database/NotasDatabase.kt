package ipvc.estg.reparecoescidade.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ipvc.estg.reparecoescidade.dao.NotasDAO
import ipvc.estg.reparecoescidade.entities.Notas

@Database(entities = arrayOf(Notas::class), version = 1)
abstract  class NotasDatabase : RoomDatabase() {
    abstract fun noteDao(): NotasDAO

    companion object {
        var instance: NotasDatabase? = null
        fun getDatabase(context: Context): NotasDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, NotasDatabase::class.java,
                        "notes_database").build()
            }
            return instance
        }
    }
}