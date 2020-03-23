package com.example.randoexpress.views.authentication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.network.RandoRepository
import com.example.randoexpress.viewmodels.AuthViewModel
import com.example.randoexpress.viewmodels.RandoListViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * Signup screen fragment
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signupButton: Button = view.findViewById(R.id.signup_button)
        val signupEmail: TextInputEditText = view.findViewById(R.id.signup_email_input_text)
        val signupFirstName: TextInputEditText = view.findViewById(R.id.signup_first_name_input_text)
        val signupLastName: TextInputEditText = view.findViewById(R.id.signup_last_name_input_text)
        val signupPassword: TextInputEditText = view.findViewById(R.id.signup_password_input_text)
        signupButton.setOnClickListener { v ->
            val newUser: Model.SignUpUser =
                Model.SignUpUser(signupFirstName.text.toString(),
                    signupLastName.text.toString(),
                    signupPassword.text.toString(),
                    signupEmail.text.toString())
            Log.i("user", "User: "+newUser)
            val authViewModel = AuthViewModel(newUser)
            authViewModel.signupUser.observe(viewLifecycleOwner, Observer { message ->
                Navigation.findNavController(v)
                    .navigate(R.id.action_signInFragment_to_loginFragment)
            })

        }
    }
}
