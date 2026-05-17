package com.gramaangana.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gramaangana.MainActivity
import com.gramaangana.data.SessionManager
import com.gramaangana.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var session: SessionManager
    private var isLoginMode = true

    // Simple in-memory user store (replace with Room/Firebase for production)
    private val registeredUsers = mutableMapOf<String, Pair<String, String>>() // email -> (name, password)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        // Skip login if already logged in
        if (session.isLoggedIn()) {
            goToMain()
            return
        }

        binding.btnAction.setOnClickListener { handleAction() }
        binding.tvToggleMode.setOnClickListener { toggleMode() }
    }

    private fun handleAction() {
        val name     = binding.etName.text.toString().trim()
        val email    = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            binding.tvError.text = "⚠️ Email and password are required"
            return
        }

        if (isLoginMode) {
            // Login
            val user = registeredUsers[email]
            if (user == null) {
                binding.tvError.text = "❌ No account found. Please register first."
            } else if (user.second != password) {
                binding.tvError.text = "❌ Incorrect password"
            } else {
                session.saveLogin(user.first, email)
                goToMain()
            }
        } else {
            // Register
            if (name.isEmpty()) {
                binding.tvError.text = "⚠️ Name is required for registration"
                return
            }
            if (registeredUsers.containsKey(email)) {
                binding.tvError.text = "❌ Account already exists. Please login."
                return
            }
            registeredUsers[email] = Pair(name, password)
            session.saveLogin(name, email)
            goToMain()
        }
    }

    private fun toggleMode() {
        isLoginMode = !isLoginMode
        if (isLoginMode) {
            binding.tvTitle.text = "Welcome Back 🙏"
            binding.tilName.visibility = android.view.View.GONE
            binding.btnAction.text = "Login"
            binding.tvToggleMode.text = "New user? Register here"
        } else {
            binding.tvTitle.text = "Create Account"
            binding.tilName.visibility = android.view.View.VISIBLE
            binding.btnAction.text = "Register"
            binding.tvToggleMode.text = "Already have an account? Login"
        }
        binding.tvError.text = ""
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
