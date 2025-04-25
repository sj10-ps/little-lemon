
package com.example.littlelemon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.llgreen
import com.example.littlelemon.ui.theme.yellow

@Composable
fun home(navController: NavHostController) {

Column(Modifier.fillMaxSize()) {
    header(navController)
    intro()
    buttons()

}


}

@Composable
fun header(navController: NavHostController) {
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
        IconButton(onClick = { navController.navigate(Profile.Route) }) {
            Image(imageVector = Icons.Default.Person, contentDescription = "",
            Modifier.height(60.dp)
                )
        }
    }
}

@Composable
fun intro(){

Column(
    Modifier
        .background(color = llgreen)
        .padding(3.dp)
        ) {
    Text(text = "Little Lemon",

        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = yellow


    )
    Row() {
        Column() {
            Text(text = "Chicago",
                fontSize = 24.sp,
                color=Color.White
            )
            Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                fontSize = 18.sp,
                color=Color.Black,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            

        }
        Image(painter = painterResource(id = R.drawable.hero) , contentDescription =""
        , modifier = Modifier.clip(RoundedCornerShape(8.dp)).padding(bottom = 4.dp)
        )
    }


}

}


@Composable
fun buttons(){
    val db = AppDatabase.getDatabase(LocalContext.current.applicationContext)
    val itemsFlow = db.menuItemsDao().getAllMenuItems().collectAsState(initial = emptyList())
    val items = itemsFlow.value
    val buttons= listOf("All","Starters","Mains","Desserts","Drinks")
    var choice by remember {
        mutableStateOf("all")
    }

    var searchitem by remember{
        mutableStateOf(TextFieldValue(""))
    }

    Column(Modifier.padding(3.dp)) {
        OutlinedTextField(value =searchitem , onValueChange = {searchitem=it}, Modifier.fillMaxWidth(),
            label = {Text(text = "search")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription ="" )
            },
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "ORDER FOR DELIVERY!",
        fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        LazyRow(Modifier.padding(3.dp)) {
            items(buttons){
                    btn->
                Button(onClick = {
                choice=btn

                },
                    Modifier.padding(end = 4.dp)
                    , colors = ButtonDefaults.buttonColors(backgroundColor = yellow)
                ) {
                    Text(text = btn)
                }
            }
        }


        menuitemsdisplay(items = items,choice=choice,search=searchitem.text)
    }

}


@Composable
fun menuitemsdisplay(items: List<MenuItemsRoom>, choice: String = "all", search: String="all") {
    val filteredItems=if(choice.lowercase()=="all"){items}else{
        items.filter {
            item->
            item.category.equals(choice.lowercase())
        }
    }

    val searchfiltered=if(search=="all"){
        filteredItems
    }else{
        filteredItems.filter {
            item->
            item.title.contains(search, ignoreCase = true)
        }
    }
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(searchfiltered) { searchfiltered ->
            Box(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(3.dp, Color.Black)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text =searchfiltered.title,
                        fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                            )
                        Text(text = searchfiltered.description,

                            fontSize = 15.sp)
                        Text(text = "$${searchfiltered.price}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,

                            )
                    }
                    Image(
                        painter = rememberAsyncImagePainter(searchfiltered.image),
                        contentDescription = searchfiltered.title,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(end = 8.dp)
                            .border(2.dp, Color.Black, RoundedCornerShape(2.dp)),
                        contentScale = ContentScale.Crop
                    )


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun homePreview(){
    val navController= rememberNavController()
    home(navController =navController)
}












