package com.example.randoexpress.views.authentication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * Signup screen fragment
 */
class SignUpFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signupButton: Button = view.findViewById(R.id.signup_button)
        signupButton.setOnClickListener { v ->
            val newUser = buildSignUpUser(view)
            Log.i("user", "User: "+newUser)
            authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
            authViewModel.user = newUser
            //authViewModel = AuthViewModel(newUser)
            authViewModel.signupUser.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, "Account successfully created!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(v)
                    .navigate(R.id.action_signInFragment_to_loginFragment)
            })

        }
    }

    /**
     * Creates new user object
     * @param view root view
     * @return signup user object
     */
    private fun buildSignUpUser(view: View): Model.SignUpUser{
        val signupEmail: TextInputEditText = view.findViewById(R.id.signup_email_input_text)
        val signupFirstName: TextInputEditText = view.findViewById(R.id.signup_first_name_input_text)
        val signupLastName: TextInputEditText = view.findViewById(R.id.signup_last_name_input_text)
        val signupPassword: TextInputEditText = view.findViewById(R.id.signup_password_input_text)
        return Model.SignUpUser(firstName = signupFirstName.text.toString(),
            name = signupLastName.text.toString(),
            password = signupPassword.text.toString(),
            mail = signupEmail.text.toString())
    }
}
