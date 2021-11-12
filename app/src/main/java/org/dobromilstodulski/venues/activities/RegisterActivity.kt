package org.dobromilstodulski.venues.activities

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
import org.dobromilstodulski.venues.databinding.ActivityRegisterBinding
import timber.log.Timber
import timber.log.Timber.i
import android.content.Intent

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Register Activity started...")

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
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val TAG = "FireBase"
    }
}