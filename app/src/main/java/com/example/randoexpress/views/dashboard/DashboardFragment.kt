package com.example.randoexpress.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.randoexpress.R

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logInButton: Button = view.findViewById(R.id.login_button_temp)
        val signInButton: Button = view.findViewById(R.id.signin_button_temp)
        logInButton.setOnClickListener { v ->
            Navigation
                .findNavController(v)
                .navigate(R.id.action_navigation_dashboard_to_loginFragment)
        }
        signInButton.setOnClickListener { v ->
            Navigation
                .findNavController(v)
                .navigate(R.id.action_navigation_dashboard_to_signInFragment)
        }
    }
}