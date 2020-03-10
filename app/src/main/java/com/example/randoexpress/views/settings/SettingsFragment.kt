package com.example.randoexpress.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.randoexpress.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createRandoButton: Button = view.findViewById(R.id.settings_create_rando_button)
        val changePasswordButton: Button = view.findViewById(R.id.settings_change_password_button)
        val logoutButton: Button = view.findViewById(R.id.settings_logout_button)
        logoutButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_navigation_settings_to_loginFragment)
        }
        createRandoButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_navigation_settings_to_loginFragment)
        }
        changePasswordButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_navigation_settings_to_loginFragment)
        }
    }
}
