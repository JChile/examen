package com.example.danp_examen

sealed class Screens(val route: String) {
    object Login: Screens("login_screen")
    object Register: Screens("register_screen")
    object List: Screens("list_screen")
}
