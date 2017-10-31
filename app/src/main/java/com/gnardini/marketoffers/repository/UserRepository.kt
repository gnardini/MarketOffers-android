package com.gnardini.marketoffers.repository

import com.gnardini.marketoffers.model.LoginObject
import com.gnardini.marketoffers.model.SignUpObject
import com.gnardini.marketoffers.repository.services.UsersServices
import com.gnardini.marketoffers.utils.StorageUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val usersService: UsersServices) {

    fun getLoggedUserId() = StorageUtils.getIntFromSharedPreferences(USER_KEY, -1)

    fun saveUserId(id: Int) = StorageUtils.storeInSharedPreferences(USER_KEY, id)

    fun login(loginObject: LoginObject, repoCallback: RepoCallback<Int>) {
        usersService
                .login(loginObject)
                .enqueue(object : Callback<Int> {
                    override fun onResponse(call: Call<Int>?, response: Response<Int>) {
                        if (response.isSuccessful) {
                            response.body()?.let { id -> repoCallback.onSuccess(id) }
                        } else {
                            repoCallback.onError("Error")
                        }

                    }

                    override fun onFailure(call: Call<Int>?, t: Throwable) {
                        repoCallback.onError("Error")
                    }
                })
    }

    fun signup(signupObject: SignUpObject, repoCallback: RepoCallback<Int>) {
        usersService
                .signup(signupObject)
                .enqueue(object : Callback<Int> {
                    override fun onResponse(call: Call<Int>?, response: Response<Int>) {
                        if (response.isSuccessful) {
                            response.body()?.let { id -> repoCallback.onSuccess(id) }
                        } else {
                            repoCallback.onError("Error")
                        }

                    }

                    override fun onFailure(call: Call<Int>?, t: Throwable) {
                        repoCallback.onError("Error")
                    }
                })
    }

    companion object {
        val USER_KEY = "USER"
    }

}
