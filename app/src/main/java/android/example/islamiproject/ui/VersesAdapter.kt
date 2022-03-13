package android.example.islamiproject.ui

import android.example.islamiproject.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VersesAdapter(var items :List<String>) : RecyclerView.Adapter<VersesAdapter.VersesViewHolder>() {

    class VersesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var ayahText : TextView

        init {
            ayahText = itemView.findViewById(R.id.ayah_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah,parent,false)
        return VersesViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VersesViewHolder, position: Int) {
        val item = items.get(position)
        holder.ayahText.text = item + " ( " + (position+1) +" ) "
    }



}