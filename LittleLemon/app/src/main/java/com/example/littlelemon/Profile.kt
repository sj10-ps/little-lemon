package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.yellow

@Composable
fun profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("littlelemonprefs", Context.MODE_PRIVATE)


    val firstname = sharedPreferences.getString("fname", "") ?: ""
    val lastname = sharedPreferences.getString("lname", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""


    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
        Row(
            Modifier

                .fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.logo)
                , contentDescription ="",
                Modifier
                    .fillMaxWidth(0.85f)
                    .width(179.dp)
                    .height(56.dp)
                    .padding(top = 3.dp)

            )
            IconButton(onClick = { navController.navigate(Home.Route) }) {
                Image(imageVector = Icons.Default.Person, contentDescription = "",
                    Modifier.height(60.dp)
                )
            }
        }



        Text(
            text = "My Profile",

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))


        Text(text = "First Name")
        TextField(
            value = firstname,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Last Name")
        TextField(
            value = lastname,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Email")
        TextField(
            value = email,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(48.dp))


        Button(
            onClick = {
                navController.navigate(OnBoarding.Route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        ) {
            Text("Logout")
        }

    }
}





@Preview(showBackground = true)
@Composable
fun profilePreview(){
    val navController= rememberNavController()
    profile(navController)
}