package com.example.randoexpress.views.authentication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.sign

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView: BottomNavigationView = activity!!.findViewById(R.id.nav_view)
        navView.visibility = View.GONE;
        val loginButton: Button = view.findViewById(R.id.login_login_button)
        val signinButton: Button = view.findViewById(R.id.login_signin_button)
        loginButton.setOnClickListener { v ->
            navView.visibility = View.VISIBLE;
            Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_navigation_home)
        }
        signinButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_signInFragment)
        }
    }
}
