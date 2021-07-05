package ipvc.estg.reparecoescidade.ViewModel

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ipvc.estg.reparecoescidade.entities.Notas
import ipvc.estg.reparecoescidade.repository.Reposiotry

class ViewModel(app:Application): AndroidViewModel(app){
    var repository: Reposiotry= Reposiotry(app)

   fun insert (note:Notas){
       repository.insert(note)
   }


    fun delete(note: Notas) {
        repository.delete(note)
    }

    fun update(note: Notas) {
        repository.update(note)
    }

    fun getAllNotes(): LiveData<List<Notas>> {
        return repository.getAllNotes()
    }
}




