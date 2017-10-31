package com.gnardini.marketoffers.utils

import android.app.Activity
import com.gnardini.marketoffers.MarketOffersApp
import com.google.gson.Gson

object StorageUtils {

    private val sp = MarketOffersApp.appContext.getSharedPreferences(
            "MarketOffersSharedPreferences",
            Activity.MODE_PRIVATE)

    fun storeInSharedPreferences(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun storeInSharedPreferences(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    fun storeInSharedPreferences(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    fun storeInSharedPreferences(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    fun storeInSharedPreferences(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    fun <T> storeInSharedPreferences(key: String, value: T) {
        val gson = Gson()
        storeInSharedPreferences(key, gson.toJson(value))
    }

    fun getStringFromSharedPreferences(key: String, defValue: String): String =
            sp.getString(key, defValue)

    fun getIntFromSharedPreferences(key: String, defValue: Int): Int =
            sp.getInt(key, defValue)

    fun getFloatFromSharedPreferences(key: String, defValue: Float): Float =
            sp.getFloat(key, defValue)

    fun getBooleanFromSharedPreferences(key: String, defValue: Boolean): Boolean =
            sp.getBoolean(key, defValue)

    fun getLongFromSharedPreferences(key: String, defValue: Long): Long =
            sp.getLong(key, defValue)

    fun <T> getObjectFromSharedPreferences(key: String, clazz: Class<T>): T {
        val stringValue = getStringFromSharedPreferences(key, "")
        val gson = Gson()
        return gson.fromJson(stringValue, clazz)
    }

    fun clearKey(key: String) = sp.edit().remove(key).apply()

    fun keyExists(key: String): Boolean = sp.contains(key)

}
