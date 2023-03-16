package com.example.firebaserealtimecrud.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firebaserealtimecrud.Modals.usermodal
import com.example.firebaserealtimecrud.R

class UserAdapter (private val listView:ArrayList<usermodal>): RecyclerView.Adapter<UserAdapter.UserViewHolder>()  {

    //for click create this
    private lateinit var mListener: OnItemClickListener

    class UserViewHolder(itemView: View,clickListener: OnItemClickListener) :RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name)
   //for clickview
        init{
            itemView.setOnClickListener{
                clickListener.OnItemClicked(adapterPosition)
            }
        }

    }

    //for click

    interface OnItemClickListener{
        fun OnItemClicked(position: Int)
    }
//
    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener=clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_user_list_view,parent,false)
        val newholder= UserViewHolder(view,mListener)
        return newholder
    }

    override fun getItemCount(): Int {
       return listView.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentitem= listView[position]
        holder.name.text=currentitem.userName
    }
}

