package com.gnardini.marketoffers.model

data class SignUpObject(
        val username: String,
        val password: String,
        val age: Int,
        val sex: Sex)