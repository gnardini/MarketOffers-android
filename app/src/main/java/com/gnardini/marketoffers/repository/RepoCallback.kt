package com.gnardini.marketoffers.repository

interface RepoCallback<in Value> {

    fun onSuccess(value: Value)

    fun onError(error: String)

}
