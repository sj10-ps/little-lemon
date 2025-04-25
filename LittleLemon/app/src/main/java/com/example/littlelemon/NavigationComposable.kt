package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



@Composable
fun navigation(context: Context,navController: NavHostController){

    val sh=context.getSharedPreferences("littlelemonprefs", Context.MODE_PRIVATE)
    val presence=sh.getString("sname",null)
    var startdestination by remember {
        mutableStateOf("")
    }
    if (presence!=null){
        startdestination=Home.Route
    }else{
        startdestination= OnBoarding.Route
    }
    NavHost(navController = navController , startDestination =startdestination ){
        composable(OnBoarding.Route) {
            onBoarding(navController)
        }

     composable(Home.Route){
         home(navController)
     }
        composable(Profile.Route){
            profile(navController)
        }
    }
}