package org.dobromilstodulski.venues.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.dobromilstodulski.venues.databinding.ActivityLoginBinding
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    private var customToken: String? = null

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        Timber.i("Login Activity started...")

        binding.btnLogin.setOnClickListener() {
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

            customToken?.let {
                auth.signInWithCustomToken(it)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "Sign In Successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, VenueActivity::class.java))
        } else {
            Toast.makeText(this, "Sign In Unsuccessful", Toast.LENGTH_LONG).show()
        }
    }

    private fun reload() {
        Toast.makeText(this, "Sign In Successful", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, VenueActivity::class.java))
    }

    companion object {
        private const val TAG = "CustomAuthActivity"
    }
}