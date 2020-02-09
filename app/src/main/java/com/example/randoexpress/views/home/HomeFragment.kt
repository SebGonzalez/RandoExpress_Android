package com.example.randoexpress.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoService
import com.example.randoexpress.network.RetrofitInstance
import com.example.randoexpress.viewmodels.RandoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    val data: ArrayList<String> = ArrayList()
    private lateinit var randoViewModel: RandoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        randoViewModel = ViewModelProviders.of(this).get(RandoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val service: RandoService = RetrofitInstance.getRetrofitInstance()!!.create(RandoService::class.java)
        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
        recyclerView.adapter = RandoListAdapter(ArrayList())
        val call: Call<ArrayList<Model.Rando>> = service.getRandos()
        call.enqueue(object : Callback<ArrayList<Model.Rando>> {
            override fun onResponse(
                call: Call<ArrayList<Model.Rando>>,
                response: Response<ArrayList<Model.Rando>>
            ) {
                if (response.isSuccessful()) {
                    val groups: ArrayList<Model.Rando> = response.body()!!
                    val adapter = RandoListAdapter(groups)
                    recyclerView.adapter = adapter
                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<ArrayList<Model.Rando>>, t: Throwable) {
                Log.e("====>MainActivity", "Something went wrong $t")
            }
        })
//        mockData()
//        val adapter = RandoListAdapter(data)
//        val recyclerView : RecyclerView = root.findViewById(R.id.rando_list)
//        recyclerView.adapter = adapter
        return root
    }


}