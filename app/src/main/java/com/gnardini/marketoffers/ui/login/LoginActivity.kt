package com.gnardini.marketoffers.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.extensions.repositoryInjector
import com.gnardini.marketoffers.extensions.showToast
import com.gnardini.marketoffers.extensions.startActivity
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.LoginObject
import com.gnardini.marketoffers.repository.RepoCallback
import com.gnardini.marketoffers.ui.offers.OffersActivity

class LoginActivity: AppCompatActivity() {

    private val username by bindView<EditText>(R.id.username)
    private val password by bindView<EditText>(R.id.password)
    private val login by bindView<View>(R.id.login)
    private val signup by bindView<View>(R.id.signup)

    private val usersRepo by lazy { repositoryInjector.usersRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        login.setOnClickListener(this::loginClicked)
        signup.setOnClickListener(this::signupClicked)
    }

    private fun loginClicked(view: View) {
        val usernameText = username.text.toString()
        if (usernameText.isEmpty()) {
            showToast("Usuario no debe estar vacio")
            return
        }
        val passwordText = password.text.toString()
        if (passwordText.isEmpty()) {
            showToast("Contraseña no debe estar vacia")
            return
        }
        val loginObject = LoginObject(usernameText, passwordText)
        usersRepo.login(loginObject, object : RepoCallback<Int> {

            override fun onSuccess(id: Int) {
                usersRepo.saveUserId(id)
                startActivity(OffersActivity::class.java)
            }

            override fun onError(error: String) {
                showToast("Error al iniciar sesion")
            }
        })
    }

    private fun signupClicked(view: View) {
        startActivity(SignupActivity::class.java)
    }

}
