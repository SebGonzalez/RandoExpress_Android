package com.example.randoexpress.views.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.RandoListViewModel


class DetailsFragment : Fragment() {

    private val randoViewModel: RandoListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val randoId = bundle!!.getInt("randoId")
        randoViewModel.getRandoList.observe(viewLifecycleOwner, Observer { list ->
            Log.i("=====>Details", "randoId"+list.get(randoId))
        })
    }
}
