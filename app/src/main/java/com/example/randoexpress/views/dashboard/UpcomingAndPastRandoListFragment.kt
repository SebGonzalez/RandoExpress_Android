package com.example.randoexpress.views.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.RandoListAdapter
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Dashboard tab fragment
 */
class UpcomingAndPastRandoListFragment : Fragment() {

    private lateinit var randoListViewModel: RandoListViewModel

    companion object {
        const val UPCOMING_RANDO_TAB = 1
        const val PAST_RANDO_TAB = 2
    }

    var selectedTab: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchFab: FloatingActionButton = view.findViewById(R.id.search_fab)
        searchFab.visibility = View.GONE
        arguments?.takeIf { it.containsKey("object") }?.apply {
            selectedTab = getInt("object")
            setupRecyclerView(view)
        }
    }

    /**
     * Setups a recycler view according to the selected tab
     * if tab number is 1, then observes getFutureRandoList
     * if tab number is 2, then observes getPastRandoList
     */
    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.rando_list)
        recyclerView.adapter = RandoListAdapter(
            ArrayList(),
            R.id.action_navigation_dashboard_to_detailsFragment
        )
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val jwt: String = sharedPref.getString("jwt", "none") as String
        val userId: Int = sharedPref.getInt("id", 0)
        Log.i("====>Dashboard", "JWT:"+jwt)
        Log.i("====>Dashboard", "User ID:"+userId)
        randoListViewModel = ViewModelProvider(this).get(RandoListViewModel::class.java)
        randoListViewModel.jwt = jwt
        randoListViewModel.id = userId
        if(selectedTab == UPCOMING_RANDO_TAB) {
            randoListViewModel.getFutureRandoList.observe(viewLifecycleOwner, Observer { list ->
                val adapter = RandoListAdapter(
                    list,
                    R.id.action_navigation_dashboard_to_detailsFragment
                )
                recyclerView.adapter = adapter
            })
        } else if(selectedTab == PAST_RANDO_TAB){
            randoListViewModel.getPastRandoList.observe(viewLifecycleOwner, Observer { list ->
                val adapter = RandoListAdapter(
                    list,
                    R.id.action_navigation_dashboard_to_detailsFragment
                )
                recyclerView.adapter = adapter
            })
        }
    }

}