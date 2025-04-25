package com.example.littlelemon

interface Destinations{
    val Route:String
}

object Home:Destinations{
    override val Route="Home"
}

object OnBoarding:Destinations{
    override val Route="OnBoarding"
}

object Profile:Destinations{
    override val Route="Profile"
}
