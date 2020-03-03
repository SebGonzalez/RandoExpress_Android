package com.example.randoexpress.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.model.Model

class RandoListAdapter(val data: ArrayList<Model.Rando>) :
    RecyclerView.Adapter<RandoListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rando_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = data[position].name
        holder.description.text = data[position].description
        holder.itemView.setOnClickListener { view ->
            Navigation
                .findNavController(view)
                .navigate(R.id.action_navigation_home_to_detailsFragment)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.rando_title)
        val description: TextView = itemView.findViewById(R.id.rando_description);

    }
}