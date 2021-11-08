package com.mungeun.gymforyou.utilities.preference

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceManger@Inject constructor(
    @ApplicationContext context: Context
) {
    private val instance: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            context.packageName, Context.MODE_PRIVATE
        )
    }


//    @Volatile  private var instance: SharedPreferences? = null
//
//    fun getInstance(context: Context): SharedPreferences {
//        if (instance == null) {
//            val appContext = context.applicationContext
//            if (appContext != null) {
//                instance = appContext.getSharedPreferences(getFileName(appContext),
//                    Context.MODE_PRIVATE)
//            } else {
//                throw IllegalArgumentException("context.getApplicationContext() returned null")
//            }
//        }
//        return instance!!
//
//    }

    var refreshToken: String
        get() = instance.value.getString(REFRESH_TOKEN, "").toString()
        set(value) {
            val editor = instance.value.edit()
            editor?.putString(REFRESH_TOKEN, value)
            editor?.apply()
        }

    var accessToken: String
        get() = instance.value.getString(ACCESS_TOKEN, "").toString()
        set(value) {
            val editor = instance.value.edit()
            editor?.putString(ACCESS_TOKEN, value)
            editor?.apply()
        }





    companion object {
        private const val REFRESH_TOKEN = "refreshToken"
        private const val ACCESS_TOKEN = "accessToken"
    }


}