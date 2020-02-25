package com.example.randoexpress.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.viewmodels.DashboardViewModel

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val logInButton: Button = root.findViewById(R.id.login_button_temp)
        val signInButton: Button = root.findViewById(R.id.signin_button_temp)
        logInButton.setOnClickListener { view->
            Navigation
                .findNavController(view)
                .navigate(R.id.action_navigation_dashboard_to_loginFragment)
        }
        signInButton.setOnClickListener { view ->
            Navigation
                .findNavController(view)
                .navigate(R.id.action_navigation_dashboard_to_signInFragment)
        }
        val textView: TextView = root.findViewById(R.id.text_notifications)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}