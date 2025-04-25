package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.llgreen
import com.example.littlelemon.ui.theme.yellow


@Composable
fun onBoarding(navController: NavHostController) {
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var context= LocalContext.current

    var space=  Spacer(modifier = Modifier.height(4.dp))
  Column(modifier = Modifier.fillMaxSize()) {
      Image(painter = painterResource(id = R.drawable.logo),
          contentDescription ="logo" ,
          modifier = Modifier
              .fillMaxWidth()
              .height(60.dp)
              .padding(top = 3.dp)

      , alignment = Alignment.Center

      )
      Spacer(modifier = Modifier.height(6.dp))

Box(modifier = Modifier
    .background(color = llgreen)
    .fillMaxWidth()
    .fillMaxHeight(.2f)
    , contentAlignment = Alignment.Center
){
    Text(text = "Lets Get You Know",
       fontSize = 20.sp)
}
      Spacer(modifier = Modifier.height(60.dp))

      Text(text = "Personal information",
          modifier = Modifier.padding(top = 2.dp),
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold

      )

      Spacer(modifier = Modifier.height(60.dp))

      TextField(value = firstname, onValueChange ={firstname=it},Modifier.fillMaxWidth()
      , label = { Text(text = "first name")})
      
      Spacer(modifier = Modifier.height(20.dp))
      
      TextField(value = lastname, onValueChange ={lastname=it},Modifier.fillMaxWidth()
      , label = {Text(text = "lastname")})

      Spacer(modifier = Modifier.height(20.dp))

      TextField(value = email, onValueChange ={email=it},Modifier.fillMaxWidth()
          , label = {Text(text = "email")})

      Spacer(modifier = Modifier.height(200.dp))
      Button(
          onClick = {
           registration(context,firstname,lastname,email)
              navController.navigate(Home.Route)
          },
          modifier = Modifier

              .fillMaxWidth(),
          colors = ButtonDefaults.buttonColors(backgroundColor = yellow)

      ) {
          Text("Register")
      }
  }

}

@Preview(showBackground = true)
@Composable
fun onBoardingPreview(){
    val navController= rememberNavController()

    onBoarding(navController)

}

fun registration(context: Context,firstname:String,lastname:String,email:String) {
val sh=context.getSharedPreferences("littlelemonprefs",Context.MODE_PRIVATE)
    sh.edit().apply{
        putString("fname",firstname)
        putString("lname",lastname)
        putString("email",email)
        apply()

    }

}