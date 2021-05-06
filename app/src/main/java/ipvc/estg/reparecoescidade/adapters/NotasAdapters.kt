package ipvc.estg.reparecoescidade.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.reparecoescidade.R

class NotasAdapters ( val notas: List<Notas>, val recyclerClik:RecyclerClick) : RecyclerView<NotasAdapters.ViewHolder>(){
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int ) : RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate((R.layout.notas,parent, false)
        return ViewHolder(view)
    }

    override  fun getItemCount(): Int{
        return notas.size
    }

    override fun onBindViewHolder (holder: ViewHolder, position: Int ){
        val note = notas.get(position)
        holder.txtTitle?.text = note.title
        holder.txtDescription?.text = note.description
    }

    inner class ViewHolder(row: View) : RecyclerView.ViewHolder(row){
        var txtTitle: TextView? = null
        var txtDescription: TextView? = null

        init {
            row.setOnclikListener{
                recyclerClik.onItemClick(adapterPosition)
            }
            this.txtTitle = row.findViewById(R.id.txTitle)
            this.txtDescription = row.findViewById(R.id.txtDescription)
        }
    }

    interface RecyclerClick{
        fun onItemClick(position: Int)
    }

    fun getNoteAt(position: Int): Notas{
        return notas.get(position)
    }
}
