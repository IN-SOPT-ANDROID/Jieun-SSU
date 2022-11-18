package org.sopt.sample.home

import android.content.Context
import android.content.SharedPreferences
import org.sopt.sample.SignInActivity

object MysharedPreferences {
    private const val myaccount: String = "account"

    fun setUserId(context: SignInActivity, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myaccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("ID", input)
        editor.commit()
    }
    fun getUserId(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myaccount, Context.MODE_PRIVATE)
        return prefs.getString("ID", "").toString()
    }
    fun setUserPw(context: Context, input: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(myaccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("PW", input)
        editor.commit()
    }
    fun getUserPw(context: SignInActivity): String {
        val prefs: SharedPreferences = context.getSharedPreferences(myaccount, Context.MODE_PRIVATE)
        return prefs.getString("PW", "").toString()
    }
    fun clearNowUser(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(myaccount, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}
