package com.mungeun.gymforyou.di

import com.mungeun.data.repository.login.LoginRepositoryImpl
import com.mungeun.data.repository.login.SignUpRepositoryImpl
import com.mungeun.domain.usecase.GymUseCase
import com.mungeun.domain.usecase.LoginUseCase
import com.mungeun.domain.usecase.SignUpUseCase
import com.mungeun.gymforyou.data.api.GymApiService
import com.mungeun.gymforyou.data.api.LoginApiService
import com.mungeun.gymforyou.data.gym.GymRepositoryImpl
import com.mungeun.gymforyou.domain.repository.GymRepository
import com.mungeun.gymforyou.domain.repository.LoginRepository
import com.mungeun.gymforyou.domain.repository.SignUpRepository
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://qazxswedc.iptime.org:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideLoginApiService(retrofit: Retrofit): LoginApiService {
//        return retrofit.create(LoginApiService::class.java)
//    }

//    @Provides
//    @Singleton
//    fun provideGymApiService(retrofit: Retrofit): GymApiService {
//        return retrofit.create(GymApiService::class.java)
//    }


    @Provides
    @Singleton
    fun provideLoginApiService(preferenceManger: PreferenceManger) : LoginApiService{
        return LoginApiService.create(preferenceManger)
    }

    @Provides
    @Singleton
    fun provideGymApiService(preferenceManger: PreferenceManger): GymApiService {
        return GymApiService.create(preferenceManger)
    }


//    @Provides
//    @Singleton
//    fun provideLoginRepository(loginApiService: LoginApiService): LoginRepository {
//        return LoginRepositoryImpl(loginApiService)
//    }


    @Provides
    @Singleton
    fun provideSignUpRepository(loginApiService: LoginApiService): SignUpRepository {
        return SignUpRepositoryImpl(loginApiService)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(signUpRepository: SignUpRepository): SignUpUseCase {
        return SignUpUseCase(signUpRepository)
    }

    @Provides
    @Singleton
    fun provideGymRepository(
        gymApiService: GymApiService,
        preferenceManger: PreferenceManger,
    ): GymRepository {
        return GymRepositoryImpl(gymApiService, preferenceManger)
    }

    @Provides
    @Singleton
    fun provideGymUseCase(gymRepository: GymRepository): GymUseCase {
        return GymUseCase(gymRepository)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginApiService: LoginApiService): LoginRepository {
        return LoginRepositoryImpl(loginApiService)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }


}