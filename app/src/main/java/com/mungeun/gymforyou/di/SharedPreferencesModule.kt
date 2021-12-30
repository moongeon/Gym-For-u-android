package com.mungeun.gymforyou.di

import android.content.Context
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun providePreferenceManger(@ApplicationContext context: Context ) : PreferenceManger {
        return PreferenceManger(context)

    }




}