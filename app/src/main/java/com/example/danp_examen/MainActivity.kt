package com.example.danp_examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.danp_examen.composables.ListPage
import com.example.danp_examen.composables.LoginPage
import com.example.danp_examen.composables.RegisterPage
import com.example.danp_examen.ui.theme.DANP_EXAMENTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}

@Composable
fun NavGraph (navController: NavHostController){
    val dataStore = DataStoreClass(LocalContext.current)
    val savedId = dataStore.getId.collectAsState(initial = -1)

    NavHost(
        navController = navController,
        startDestination = determineStartDestination(savedId.value)

    ) {
        composable(route = Screens.Login.route){
            LoginPage(navController)
        }
        composable(route = Screens.Register.route){
            RegisterPage(navController)
        }
        composable(route = Screens.List.route) {
            ListPage(navController)
        }

    }
}

private fun determineStartDestination(savedId: Int?): String {
    return if (savedId != -1) {
        Screens.List.route
    } else {
        Screens.Login.route
    }
}

