package com.example.re_watch.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.re_watch.R
import com.example.re_watch.components.CButton
import com.example.re_watch.components.DontHaveAccountRow
import com.example.re_watch.navigation.AppScreens
import com.example.re_watch.presetation.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController,
                viewModel: SignInViewModel = hiltViewModel()
) {
    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {


        val scope = rememberCoroutineScope()
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        val context = LocalContext.current
        val state = viewModel.signInState.collectAsState(initial = null)

        Box(modifier = Modifier.fillMaxSize()) {
            /// Background Image
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .align(alignment = Alignment.BottomCenter)
            )

            /// Content

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.pheonix),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 54.dp)
                        .height(100.dp)
                        .align(Alignment.Start)
                        .offset(x = (-20).dp)
                )

                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text(
                    "Sign In now to access all the features of Re-watch.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xB2FFFFFF)
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )


                // Text Field
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = {
                        Text(
                            text = "Email",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color(0xFFBEC2C2)
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFBEC2C2),
                        unfocusedTextColor = Color(0xFFBEC2C2),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFBEC2C2),
                        unfocusedIndicatorColor = Color(0xFFBEC2C2),
                    )
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = {
                        Text(
                            text = "Password",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color(0xFFBEC2C2)
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFBEC2C2),
                        unfocusedTextColor = Color(0xFFBEC2C2),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFBEC2C2),
                        unfocusedIndicatorColor = Color(0xFFBEC2C2),
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                CButton(text = "Sign In",
                    onClick = {
                        scope.launch {
                            viewModel.loginUser(email, password)
                        }
                    })

                DontHaveAccountRow(
                    onSignupTap = {
                        navController.navigate(route = AppScreens.SignUpScreen.name)
                    }
                )
            }

            LaunchedEffect(key1 = state.value?.isSuccess ) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context,"$success", Toast.LENGTH_LONG).show()
                    }
                }
            }
            LaunchedEffect(key1 = state.value?.isError ) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context,"$error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
