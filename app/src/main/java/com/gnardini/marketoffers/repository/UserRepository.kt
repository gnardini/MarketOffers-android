package com.gnardini.marketoffers.repository

import com.gnardini.marketoffers.model.LoginObject
import com.gnardini.marketoffers.model.SignUpObject
import com.gnardini.marketoffers.repository.services.UsersServices
import com.gnardini.marketoffers.utils.StorageUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val usersService: UsersServices) {

    fun isUserLoggedIn() = StorageUtils.keyExists(USER_KEY)

    fun getLoggedUserId() = StorageUtils.getStringFromSharedPreferences(USER_KEY, "")

    fun saveUserId(id: String) = StorageUtils.storeInSharedPreferences(USER_KEY, id)

    fun logout() = StorageUtils.clearKey(USER_KEY)

    fun login(loginObject: LoginObject, repoCallback: RepoCallback<String>) {
        usersService
                .login(loginObject)
                .enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>?, response: Response<String>) {
                        if (response.isSuccessful) {
                            response.body()?.let { id -> repoCallback.onSuccess(id) }
                        } else {
                            repoCallback.onError("Error")
                        }

                    }

                    override fun onFailure(call: Call<String>?, t: Throwable) {
                        repoCallback.onError("Error")
                    }
                })
    }

    fun signup(signupObject: SignUpObject, repoCallback: RepoCallback<String>) {
        usersService
                .signup(signupObject)
                .enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>?, response: Response<String>) {
                        if (response.isSuccessful) {
                            response.body()?.let { id -> repoCallback.onSuccess(id) }
                        } else {
                            repoCallback.onError("Error")
                        }

                    }

                    override fun onFailure(call: Call<String>?, t: Throwable) {
                        repoCallback.onError("Error")
                    }
                })
    }

    companion object {
        val USER_KEY = "USER"
    }

}

