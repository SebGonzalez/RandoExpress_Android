package com.example.randoexpress.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.randoexpress.R
import com.example.randoexpress.RandoListAdapter
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpcomingAndPastRandoListFragment : Fragment() {

    companion object {
        const val UPCOMING_RANDO_TAB = 1
        const val PAST_RANDO_TAB = 2
    }

    private val randoViewModel: RandoListViewModel by activityViewModels()
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
        if(selectedTab == UPCOMING_RANDO_TAB) {
            randoViewModel.getFutureRandoList.observe(viewLifecycleOwner, Observer { list ->
                val adapter = RandoListAdapter(
                    list,
                    R.id.action_navigation_dashboard_to_detailsFragment
                )
                recyclerView.adapter = adapter
            })
        } else if(selectedTab == PAST_RANDO_TAB){
            randoViewModel.getPastRandoList.observe(viewLifecycleOwner, Observer { list ->
                val adapter = RandoListAdapter(
                    list,
                    R.id.action_navigation_dashboard_to_detailsFragment
                )
                recyclerView.adapter = adapter
            })
        }
    }

}