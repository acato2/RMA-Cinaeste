package com.example.cinaeste.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.R
import com.example.cinaeste.data.Movie

class SimpleStringAdapter(list :List<String>):
      RecyclerView.Adapter<SimpleStringAdapter.SimpleViewHolder>(){
      var list = list
      inner class SimpleViewHolder(view: View):RecyclerView.ViewHolder(view){
           val textElement = itemView.findViewById<TextView>(android.R.id.text1)
      }

      //kreiraj novi view
      override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
      ): SimpleStringAdapter.SimpleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,parent,false)
            return SimpleViewHolder(view)
      }
      //izmjeni sadrzaj viewa
      override fun onBindViewHolder(holder: SimpleStringAdapter.SimpleViewHolder, position: Int) {
            holder.textElement.text=list[position]

      }
      override fun getItemCount(): Int = list.size


}