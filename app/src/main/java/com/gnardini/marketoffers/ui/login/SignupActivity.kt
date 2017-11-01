package com.gnardini.marketoffers.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.extensions.repositoryInjector
import com.gnardini.marketoffers.extensions.showToast
import com.gnardini.marketoffers.extensions.startActivityClearingTask
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Sex
import com.gnardini.marketoffers.model.SignUpObject
import com.gnardini.marketoffers.repository.RepoCallback
import com.gnardini.marketoffers.ui.offers.OffersActivity

class SignupActivity: AppCompatActivity() {

    private val username by bindView<EditText>(R.id.username)
    private val password by bindView<EditText>(R.id.password)
    private val age by bindView<EditText>(R.id.age)
    private val sex by bindView<Spinner>(R.id.sex)
    private val signup by bindView<View>(R.id.signup)

    private val usersRepo by lazy { repositoryInjector.usersRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        signup.setOnClickListener(this::signupClicked)
    }

    fun signupClicked(view: View) {
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
        val ageText = age.text.toString()
        if (ageText.isEmpty()) {
            showToast("La edad no debe estar vacia")
            return
        }
        if (ageText.length > 2) {
            showToast("La edad no puede ser tan grande")
            return
        }
        val ageNumber = Integer.parseInt(ageText)
        val sexChosen = Sex.values()[sex.selectedItemPosition]
        val signupObject = SignUpObject(usernameText, passwordText, ageNumber, sexChosen)
        usersRepo.signup(signupObject, object : RepoCallback<String> {

            override fun onSuccess(id: String) {
                usersRepo.saveUserId(id)
                startActivityClearingTask(OffersActivity::class.java)
            }

            override fun onError(error: String) {
                showToast("Error al crear usuario")
            }
        })
    }

}
