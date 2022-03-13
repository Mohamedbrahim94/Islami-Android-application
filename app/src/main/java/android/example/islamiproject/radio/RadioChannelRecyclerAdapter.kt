package android.example.islamiproject.radio

import android.example.islamiproject.R
import android.example.islamiproject.api.responses.RadiosItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

var channels: MutableList<RadiosItem>? = null
class RadioChannelRecyclerAdapter() : RecyclerView.Adapter<RadioChannelRecyclerAdapter.radioChannelViewHolder>() {


    class radioChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.radioChannelTextView)
        var play: ImageView = itemView.findViewById(R.id.playButtonImageView)
        var stop: ImageView = itemView.findViewById(R.id.stopButtonImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): radioChannelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio_channels, parent, false)

        return radioChannelViewHolder(view)
    }


    override fun onBindViewHolder(holder: radioChannelViewHolder, position: Int) {
        var channel = channels?.get(position)
        holder.name.setText(channel?.name)
        if (internalOnPlayClick != null) {
            holder.play.setOnClickListener {
                internalOnPlayClick?.onItemClick(position, channel!!)
            }
        }
        if (internalOnStopClick != null) {
            holder.stop.setOnClickListener {
                internalOnStopClick?.onItemClick(position, channel!!)

            }
        }
    }

    override fun getItemCount(): Int {
        return channels?.size ?: 0
    }


    fun setOnPlayClick(onPlayClick: onPlayClickListener) {
        internalOnPlayClick = onPlayClick
    }

    var internalOnPlayClick: onPlayClickListener? = null

    interface onPlayClickListener {
        fun onItemClick(position: Int, channel: RadiosItem)

    }

    fun setOnStopClick(onStopClick: onStopClickListener) {
        internalOnStopClick = onStopClick
    }

    var internalOnStopClick: onStopClickListener? = null

    interface onStopClickListener {
        fun onItemClick(position: Int, channel: RadiosItem)
    }

    fun changeData(radiosChannels: MutableList<RadiosItem>) {
        channels = radiosChannels
        notifyDataSetChanged()
    }
}