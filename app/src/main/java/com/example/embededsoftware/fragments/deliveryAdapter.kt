package com.example.embededsoftware.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.embededsoftware.R
import kotlinx.android.synthetic.main.delivery_model.view.*

class deliveryAdapter: RecyclerView.Adapter<deliveryAdapter.ViewHolder>(){
    var items = ArrayList<DeliveryModel>()
    lateinit var listener: OnDeliveryModelClickListener

    interface OnDeliveryModelClickListener {
        fun onItemClick(holder: deliveryAdapter.ViewHolder?, view: View?, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.delivery_model,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init{
            //길게 눌렀을 때
            itemView.setOnLongClickListener{
                listener?.onItemClick(this,itemView,adapterPosition)
                return@setOnLongClickListener true
            }
        }

        //가져온 정보들 출력
        fun setItem(item: DeliveryModel){
            itemView.arriveDayText.text=item.arriveday
            itemView.arriveTimeText.text=item.arrivetime
            itemView.receiveTimeText.text=item.receivetime
            itemView.receiveDayText.text=item.receiveday
            itemView.receiveText.text=item.receive
            itemView.checkDayText.text=item.checkday
            itemView.checkTimeText.text=item.checktime
            if(item.receive == "수령완료" ){
                itemView.getText.text="수령시간"
            }
            if(item.receive == "도난 의심"){
                itemView.getText.text="도난시간"
            }
            if(item.receive == "수령 확인")
            {
                itemView.getText.text="수령시간"
                itemView.checkText.text="확인시간"
            }
            if(item.receive == "도난 확인"){
                itemView.getText.text="도난시간"
                itemView.checkText.text="확인시간"
            }
        }
    }
}