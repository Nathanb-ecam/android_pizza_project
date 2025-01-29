package com.example.pizza_app_android.ui.app_screens


import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pizza_app_android.R
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Login
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.UserViewModel

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun LoginScreen(navController: NavController,orderViewModel: OrderViewModel,userViewModel : UserViewModel = viewModel()){

    // need to fetch all authorized users

    val whiteOutlineTextFieldStyles = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MyPalette.borderLightWhite,
        placeholderColor = MyPalette.borderLightWhite,
        cursorColor = MyPalette.borderLightWhite,
        focusedLabelColor = MyPalette.borderLightWhite,
        unfocusedLabelColor = MyPalette.borderLightWhite,
        focusedBorderColor = MyPalette.borderLightWhite,
        unfocusedBorderColor = MyPalette.borderLightWhite
    )

    var username by rememberSaveable() { mutableStateOf("Nathan") }
    var password by rememberSaveable() { mutableStateOf("1234") }
    val uiState by userViewModel.uiState.collectAsState()

    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState.loggedIn){
        if (uiState.loggedIn){
            navController.navigate(Screen.MenuScreen.route)
        }
    }

    Box(modifier = Modifier.background(Color.White)){
        Column(
            modifier = Modifier.fillMaxSize().padding(
                start = 20.dp, end = 20.dp,
                top = 10.dp, bottom = 10.dp
            ),
            //verticalArrangement = Arrangement.SpaceEvenly
        ){
            //Header
            Column(modifier = Modifier.weight(.3f), verticalArrangement = Arrangement.Center){
                Text("Welcome back", fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
                Text("Log back into your account", fontSize = 16.sp, color = MyPalette.Gray)
            }


            Column(modifier = Modifier.fillMaxWidth().weight(.7f)){

                // log in form
                Column(
                    modifier = Modifier
                        .fillMaxWidth()//.weight(.7f)
                        .clip(RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp,
                            bottomEnd = 50.dp,
                            bottomStart = 10.dp
                        ))
                        .background(MyPalette.PrimaryColor).padding(25.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    //horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text("Log in", fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), color = MyPalette.White, fontWeight = FontWeight.ExtraBold)
                    Spacer(modifier = Modifier.height(25.dp))

                    if (!uiState.errMessage.isNullOrEmpty()){
                        Text(text="${uiState.errMessage}", color = MyPalette.redInfo,fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedTextField(
                        colors = whiteOutlineTextFieldStyles,modifier = Modifier.fillMaxWidth(),
                        value = username, onValueChange = { username=it },
                        singleLine = true,
                        label = {Text(text="Username", fontSize = 16.sp)},
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) } // Move to next field
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(colors = whiteOutlineTextFieldStyles, modifier = Modifier.fillMaxWidth(),
                        value = password,
                        singleLine = true,
                        onValueChange = { password=it },
                        label = {Text(text="Password", fontSize = 16.sp)},visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() } // Close keyboard
                        )
                        /*         keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus() // Hide keyboard
                                userViewModel.authentificate(orderViewModel, Login(username, password)) // Call login action
                            }
                        )*/
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Forgot password ?",
                        fontSize = 14.sp,
                        color = MyPalette.borderLightWhite,
                        modifier = Modifier.align(Alignment.End),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                        onClick = {userViewModel.authentificate(orderViewModel, Login(username,password));},
                        shape = RoundedCornerShape(35),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.Black, backgroundColor = MyPalette.White)
                    ){
                        Text(text = "Login".uppercase(), fontSize = 16.sp)
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))
                // sub form
                Row(
                    modifier = Modifier.fillMaxWidth(), // .weight(.15f)
                    horizontalArrangement = Arrangement.Center
                ){

                    Text("Don't have an account ?", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text("Join now", fontSize = 16.sp, color = MyPalette.PrimaryColor)

                }
            }



        }

    }







}

@OptIn(ExperimentalGraphicsApi::class)
@Preview
@Composable
fun PreviewLoginScreen(){
    val userViewModel = UserViewModel()
    var username by rememberSaveable() { mutableStateOf("") }
    var password by rememberSaveable() { mutableStateOf("") }
    val uiState by userViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val orderViewModel = OrderViewModel()
    LoginScreen(navController = navController, orderViewModel = orderViewModel)
}