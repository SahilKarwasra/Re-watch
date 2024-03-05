package com.example.re_watch.di

import com.example.re_watch.data.AuthRepository
import com.example.re_watch.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton


@Module
@InstallIn
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImp(firebaseAuth: FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }

}