package com.example.re_watch.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.re_watch.data.AuthRepository
import com.example.re_watch.utils.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email:String,password:String) = viewModelScope.launch {
        repository.login(email,password).collect{result ->
            when(result){
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success "))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error ->{
                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }

}