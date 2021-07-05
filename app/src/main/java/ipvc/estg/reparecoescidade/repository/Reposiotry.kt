package ipvc.estg.reparecoescidade.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import ipvc.estg.reparecoescidade.dao.NotasDAO
import ipvc.estg.reparecoescidade.database.NotasDatabase
import ipvc.estg.reparecoescidade.entities.Notas


class Reposiotry(app: Application){

    var noteDao: NotasDAO? = NotasDatabase.getDatabase(app)?.noteDao()

    fun insert (note: Notas){
        InsertAsync(noteDao).execute(note)
    }


    fun delete(note: Notas){
        DeleteAsync(noteDao).execute(note)
    }
     fun update (note: Notas){
         UpdateAsync(noteDao).execute(note)
     }


  fun getAllNotes(): LiveData<List<Notas>>{
      return GetAllNotesAysnc(noteDao).execute().get()
  }
    class InsertAsync(noteDao: NotasDAO??): AsyncTask<Notas, Void, Unit>() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
            noteDao?.insert(p0[0])
        }

    }


    class DeleteAsync(noteDao: NotasDAO?): AsyncTask<Notas, Void, Unit>() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
            noteDao?.delete(p0[0])
        }

    }
    class UpdateAsync(noteDao: NotasDAO?): AsyncTask<Notas, Void, Unit>() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
            noteDao?.update(p0[0])
        }

    }

class GetAllNotesAysnc(noteDao: NotasDAO?):AsyncTask<Unit, Void, LiveData<List<Notas>>>() {
    var noteDao= noteDao
    override fun doInBackground(vararg p0: Unit?): LiveData<List<Notas>>?{
        return noteDao?.getAllNotes()!!
    }

}



    }
    





