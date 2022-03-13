package android.example.islamiproject.ui

import android.example.islamiproject.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SuraAdapter(var items: List<String>) : RecyclerView.Adapter<SuraAdapter.suraViewHolder>() {

    class suraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var suraName: TextView

        init {
            suraName = itemView.findViewById(R.id.sura_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): suraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sura, parent, false)
        return suraViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: suraViewHolder, position: Int) {
        val item = items.get(position)
        holder.suraName.text = item
        holder.suraName.setOnClickListener{
            if (onTextClickListener != null )
                onTextClickListener.onItemClick(position,item)
        }
    }


    interface onItemClickListener {
        fun onItemClick(position: Int , suraName : String)
    }
    lateinit var onTextClickListener : onItemClickListener
    fun setonTextClickListener(onTextClick : onItemClickListener){
        onTextClickListener = onTextClick

}

}