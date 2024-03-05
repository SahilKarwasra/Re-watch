package com.example.re_watch.data

import com.example.re_watch.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email:String,password:String): Flow<Resource<AuthResult>>
    fun registerUser(email: String,password: String): Flow<Resource<AuthResult>>
}