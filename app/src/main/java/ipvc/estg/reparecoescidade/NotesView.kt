package ipvc.estg.reparecoescidade

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.reparecoescidade.ViewModel.ViewModel
import ipvc.estg.reparecoescidade.adapters.NotasAdapters
import ipvc.estg.reparecoescidade.entities.Notas
import kotlinx.android.synthetic.main.notas.*

class NotesView :  AppCompatActivity(), NotasAdapters.RecyclerClick{
    override fun onItemClick(position: Int) {

        val note = noteAdapter.getNoteAt(position)
        val id = note.id

        var editIntent = Intent(this, EditActivity::class.java)
        var title = note.title
        var description = note.description

        editIntent.putExtra("title", title)
        editIntent.putExtra("description", description)

        startActivityForResult(editIntent,
            edit
        )

        Log.e("NotesViewclick", title + description + position)
    }

    override fun Notas(id: String): Notas {
        TODO("Not yet implemented")
    }

    companion object {
        val add = 1
        val edit = 2
    }

    lateinit var viewModel: ViewModel
    lateinit var noteAdapter: NotasAdapters
    lateinit var getAllNotes: LiveData<List<Notas>>
    lateinit var allNotes: List<Notas>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notas)
        //Swipe recycler view items on RIGHT
        val helper by lazy {
            object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var position = viewHolder.adapterPosition
                    viewModel.delete(allNotes.get(position))
                    Toast.makeText(applicationContext, "Nota Eliminada", Toast.LENGTH_SHORT).show()
                }
            }
        }


        //Attaching ViewModel
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)


        //Live Data
        getAllNotes = viewModel.getAllNotes()
        getAllNotes.observe(this, Observer {

            //update RecyclerView
            allNotes = getAllNotes.value!!
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = NotasAdapters(allNotes, this)
            noteAdapter = NotasAdapters(allNotes, this)
            val swipe = ItemTouchHelper(helper)
            swipe.attachToRecyclerView(recyclerView)
        })

        //Floating action button
        fab.setOnClickListener {
            var addIntent = Intent(this, AddActivity::class.java)
            startActivityForResult(addIntent,add)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NotesView.add && resultCode == Activity.RESULT_OK) {


            var title = data?.getStringExtra("title")
            var description =  data?.getStringExtra("description")
            val note = Notas(0,title.toString(),description.toString())
            viewModel.insert(note)
            Toast.makeText(applicationContext,"Note Saved", Toast.LENGTH_SHORT).show()
        }
        if(requestCode== NotesView.edit &&resultCode== Activity.RESULT_OK){

            var title = data?.getStringExtra("title")
            var description =  data?.getStringExtra("description")
            var id = data?.getStringExtra("id")

            Toast.makeText(applicationContext,"Note Updated", Toast.LENGTH_SHORT).show()
            val note  = Notas(id?.toInt()!!,title.toString(),description.toString())
            viewModel.update(note)
        }
    }
}