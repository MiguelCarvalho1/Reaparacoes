package ipvc.estg.reparecoescidade.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "notas_tabela")
data class Notas (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val description: String){

}
