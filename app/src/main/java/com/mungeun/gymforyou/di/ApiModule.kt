package com.mungeun.gymforyou.di


import com.mungeun.data.api.GymApiService
import com.mungeun.data.api.LoginApiService
import com.mungeun.data.api.SignupApiService
import com.mungeun.data.gym.GymRepositoryImpl
import com.mungeun.data.login.LoginRepositoryImpl
import com.mungeun.data.signup.SignUpRepositoryImpl
import com.mungeun.domain.repository.GymRepository
import com.mungeun.domain.repository.LoginRepository
import com.mungeun.domain.repository.SignUpRepository
import com.mungeun.domain.usecase.GymUseCase

import com.mungeun.domain.usecase.LoginUseCase
import com.mungeun.domain.usecase.SignUpUseCase
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
    fun provideOkHttpClient(
        preferenceManger: PreferenceManger
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .addInterceptor(Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .addHeader("Authorization", "Bearer " + preferenceManger.accessToken)
                        .build()
                    proceed(newRequest)
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://qazxswedc.iptime.org:3000/")
            .client(okHttpClient)
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

    // 회원가입
    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignupApiService {
        return retrofit.create(SignupApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(signupApiService: SignupApiService): SignUpRepository {
        return SignUpRepositoryImpl(signupApiService)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(signUpRepository: SignUpRepository): SignUpUseCase {
        return SignUpUseCase(signUpRepository)
    }


    // 헬스장
    @Provides
    @Singleton
    fun provideGymApiService(retrofit: Retrofit): GymApiService {
        return retrofit.create(GymApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGymRepository(
        gymApiService: GymApiService,
        preferenceManger: PreferenceManger,
    ): GymRepository {
        return GymRepositoryImpl(gymApiService)
    }

    @Provides
    @Singleton
    fun provideGymUseCase(gymRepository: GymRepository): GymUseCase {
        return GymUseCase(gymRepository)
    }


    // 로그인
    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
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