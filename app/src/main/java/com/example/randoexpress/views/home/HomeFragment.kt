package com.example.randoexpress.views.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.RandoListAdapter
import com.example.randoexpress.viewmodels.AuthViewModel
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var randoListViewModel: RandoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
    }

    /**
     * TODO Implement search
     */
    private fun setupSearch(view: View) {
        val searchFab: FloatingActionButton = view.findViewById(R.id.search_fab)
        val searchBox: EditText = view.findViewById(R.id.search_box)
        searchFab.setOnClickListener {
            if (searchBox.isGone)
                searchBox.visibility = View.VISIBLE
            else
                searchBox.visibility = View.GONE
        }
    }

    /**
     * Fetches data from view model and passing it into adapter
     * @param fragment root view
     */
    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.rando_list)
        recyclerView.adapter = RandoListAdapter(
            ArrayList(),
            R.id.action_navigation_home_to_detailsFragment
        )
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val jwt: String = sharedPref.getString("jwt", "none") as String
        Log.i("====>Home fragment", "JWT:"+jwt)
        randoListViewModel = ViewModelProvider(this).get(RandoListViewModel::class.java)
        randoListViewModel.jwt = jwt
        randoListViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            Log.i("==>Home fragment result", "List:"+list)
            val adapter = RandoListAdapter(
                list,
                R.id.action_navigation_home_to_detailsFragment
            )
            recyclerView.adapter = adapter
        })
    }
}