package com.example.randoexpress.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import kotlinx.android.synthetic.main.rando_item.*
import org.w3c.dom.Text

class RandoListAdapter(val data : ArrayList<Model.Rando>) : RecyclerView.Adapter<RandoListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rando_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: String = data[position].name
        val description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Morbi tristique senectus et netus et. Congue nisi vitae suscipit tellus mauris a diam maecenas."
        holder.title.text = item
        holder.description.text = data[position].description
        holder.itemView.setOnClickListener { view ->  Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_detailsFragment) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.rando_title)
        val description: TextView = itemView.findViewById(R.id.rando_description);

    }
}