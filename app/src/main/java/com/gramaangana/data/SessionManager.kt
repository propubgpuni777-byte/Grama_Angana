package com.gramaangana.data

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("grama_session", Context.MODE_PRIVATE)

    fun saveLogin(name: String, email: String) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("user_name", name)
            .putString("user_email", email)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun getUserName(): String = prefs.getString("user_name", "User") ?: "User"

    fun getUserEmail(): String = prefs.getString("user_email", "") ?: ""

    fun logout() = prefs.edit().clear().apply()
}
