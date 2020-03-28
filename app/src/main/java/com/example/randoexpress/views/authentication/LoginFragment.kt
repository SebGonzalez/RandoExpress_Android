package com.example.randoexpress.views.authentication


import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.randoexpress.R
import com.example.randoexpress.model.Model
import com.example.randoexpress.viewmodels.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

/**
 * Login screen fragment
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView: BottomNavigationView = activity!!.findViewById(R.id.nav_view)
        navView.visibility = View.GONE;
        val loginButton: Button = view.findViewById(R.id.login_login_button)
        val signinButton: Button = view.findViewById(R.id.login_signin_button)
        // fetching user data and moving to home screen
        loginButton.setOnClickListener { v ->
            val loginEmail: TextInputEditText = view.findViewById(R.id.login_email_input_text)
            val loginPassword: TextInputEditText = view.findViewById(R.id.login_password_input_text)
            val loginUser: Model.LoginUser =
                Model.LoginUser(loginEmail.text.toString(),
                    loginPassword.text.toString())
            Log.i("====>LoginFragment", "Input"+loginUser)
            val loginViewModel = LoginViewModel(loginUser)
            loginViewModel.loginUser.observe(viewLifecycleOwner, Observer { user ->
                Log.i("====>LoginFragment", "User data: "+user)
                // saving user data in shared preferences for later use
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@Observer
                with (sharedPref.edit()) {
                    putString("firstName", user.firstName)
                    putString("name", user.name)
                    putString("email", user.mail)
                    putString("jwt", user.jwt)
                    putString("password", user.password)
                    putInt("id", user.id)
                    commit()
                }
                navView.visibility = View.VISIBLE
                view.hideKeyboard()
                Navigation.findNavController(v)
                    .navigate(R.id.action_loginFragment_to_navigation_home)
            })
        }
        // move to signup screen
        signinButton.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_loginFragment_to_signInFragment)
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
