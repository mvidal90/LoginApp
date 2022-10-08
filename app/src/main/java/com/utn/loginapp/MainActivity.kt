package com.utn.loginapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickButtonConfig()
        onChangeInputConfig()
    }

    private fun onClickButtonConfig() {

        val hintSuccess: TextView = findViewById(R.id.hint_success)
        val userInput: EditText = findViewById(R.id.input_user)
        val passwordInput: EditText = findViewById(R.id.input_password)
        val hintUser: TextView = findViewById(R.id.hint_user)
        val hintPassword: TextView = findViewById(R.id.hint_password)
        val submitButton: Button = findViewById(R.id.button_submit)

        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                var success = true;

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userInput.text).matches()) {
                    hintUser.visibility = View.VISIBLE
                    success = false
                }
                if (!isValidPassword(passwordInput.text)) {
                    hintPassword.visibility = View.VISIBLE
                    success = false
                }
                if (success) hintSuccess.visibility = View.VISIBLE
            }
        }
        submitButton.setOnClickListener(clickListener)
    }

    private fun isValidPassword(password: Editable): Boolean {
        val pattern: Pattern
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%^&+*=!”·&/(){}?¿,.;:])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(passwordRegex)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun onChangeInputConfig() {
        val userInput: EditText = findViewById(R.id.input_user)
        val passwordInput: EditText = findViewById(R.id.input_password)
        val hintSuccess: TextView = findViewById(R.id.hint_success)
        val hintUser: TextView = findViewById(R.id.hint_user)
        val hintPassword: TextView = findViewById(R.id.hint_password)

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                cleanHint(hintSuccess)
                cleanHint(hintUser)
                cleanHint(hintPassword)
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {}
        }
        userInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)
    }

    private fun cleanHint (element: TextView) {
        if (element.isVisible) {
            element.visibility = View.INVISIBLE
        }
    }
}