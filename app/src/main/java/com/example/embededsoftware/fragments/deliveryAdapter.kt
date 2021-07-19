package com.example.embededsoftware.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.embededsoftware.R
import kotlinx.android.synthetic.main.delivery.view.*

class deliveryAdapter : RecyclerView.Adapter<deliveryAdapter.ViewHolder>() {

    var items = ArrayList<delivery>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.delivery,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(item: delivery){
            itemView.timeText.text=item.time
            itemView.dayText.text=item.day
            itemView.receiveText.text=item.receive

        }
    }
}