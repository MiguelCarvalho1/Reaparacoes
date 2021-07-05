package ipvc.estg.reparecoescidade.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.reparecoescidade.entities.Notas


@Dao

interface NotasDAO {

    @Insert
    fun insert (note: Notas)

    @Update
    fun update (note: Notas)

    @Delete
    fun delete (note: Notas)

    @Query("SELECT * FROM NOTAS_TABLE table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Notas>>
}