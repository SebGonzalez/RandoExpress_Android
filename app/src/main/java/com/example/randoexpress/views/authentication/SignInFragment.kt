package com.example.randoexpress.views.authentication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.randoexpress.R
import kotlin.math.sign

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signinButton: Button = view.findViewById(R.id.signin_button)
        signinButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_signInFragment_to_loginFragment)
        }
    }
}
