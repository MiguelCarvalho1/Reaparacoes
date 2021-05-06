package ipvc.estg.reparecoescidade.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import ipvc.estg.reparecoescidade.dao.NotasDAO
import ipvc.estg.reparecoescidade.database.NotasDatabase

class Reposiotry(app: Application){
    var noteDao: NotasDAO? = NotasDatabase.getDatabase(app)?.noteDao()

    fun insert (note: Notas){
        InsertAsync(notasDao).execute(note)
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
    class InsertAsync(notasDao: NotasDAO?) : AsyncTask<Notas, Void, Unit(){
      var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
          noteDao?.insert(p0[0])
        }

    }


    class DeleteAsync(noteDao: NotasDAO?): AsyncTask<Notas, Void, Unit() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
            noteDao?.delete(p0[0])
        }

    }
    class UpdateAsync(noteDao: NotasDAO?): AsyncTask<Notas, Void, Unit() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Notas) {
            noteDao?.update(p0[0])
        }

    }

    class GetAllNotesAysnc(noteDao: NotasDAO?): AsyncTask<Notas, Void, Unit() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0:Unit?) {
            return noteDao?.getAllNotes()
        }

    }
    

}



