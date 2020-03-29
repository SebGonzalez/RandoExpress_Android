package com.example.randoexpress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.model.Model


/**
 * Adapter for displaying list of randos
 * @param data ArrayList of randos to display
 * @param destination action navigation for pressing on item
 */
class RandoListAdapter(val data: ArrayList<Model.Rando>, val destination: Int) :
    RecyclerView.Adapter<RandoListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rando_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * Binds data to UI
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = data[position].name
        holder.description.text = data[position].description
        holder.hostName.text = data[position].owner.firstName
        holder.attendees.text = data[position].persons.size.toString()
        holder.location.text = data[position].ville
        holder.dateTime.text = "${data[position].heureDepart} ${data[position].dateDepart}"
        val bundle = Bundle()
        bundle.putInt("randoId", data[position].id as Int)
        bundle.putInt("randoOrigin", destination)
        holder.itemView.setOnClickListener { view ->
            Navigation
                .findNavController(view)
                .navigate(destination, bundle)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.rando_title)
        val description: TextView = itemView.findViewById(R.id.rando_item_description)
        val hostName: TextView = itemView.findViewById(R.id.rando_item_host_name_value)
        val location: TextView = itemView.findViewById(R.id.rando_item_location_value)
        val attendees: TextView = itemView.findViewById(R.id.rando_item_attending_number)
        val dateTime: TextView = itemView.findViewById(R.id.rando_item_time_value)
    }
}