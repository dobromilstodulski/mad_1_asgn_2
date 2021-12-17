package org.dobromilstodulski.venues.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.activities.*
import org.dobromilstodulski.venues.databinding.FragmentRegisterBinding
import timber.log.Timber

//https://firebase.google.com/docs/auth/android/start
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private var customToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(activity, "Registration Successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(activity, MenuActivity::class.java))
        } else {
            Toast.makeText(activity, "Registration Unsuccessful", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val TAG = "CustomAuthActivity"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        Timber.plant(Timber.DebugTree())

        Timber.i("Register Activity started...")

        binding.btnRegister.setOnClickListener() {
            if (binding.emailAddress.text.toString().isEmpty()) {
                Snackbar
                    .make(it,"Please Enter an Email Address", Snackbar.LENGTH_LONG)
                    .show()
                binding.emailAddress.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailAddress.text.toString()).matches()) {
                Snackbar
                    .make(it,"Please Enter a Valid Email Address", Snackbar.LENGTH_LONG)
                    .show()
                binding.emailAddress.requestFocus()
                return@setOnClickListener
            }

            if (binding.password.text.toString().isEmpty()) {
                Snackbar
                    .make(it,"Please Enter a Password", Snackbar.LENGTH_LONG)
                    .show()
                binding.password.requestFocus()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(binding.emailAddress.text.toString(), binding.password.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}