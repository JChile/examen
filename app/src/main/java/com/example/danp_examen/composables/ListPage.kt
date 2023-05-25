package com.example.danp_examen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.example.danp_examen.DataStoreClass
import com.example.danp_examen.R
import com.example.danp_examen.Screens
import com.example.danp_examen.entities.PotholeEntity
import com.example.danp_examen.ui.theme.DarkOrange
import com.example.danp_examen.ui.theme.LGray
import com.example.danp_examen.viewmodel.PotholeViewModel
import com.example.danp_examen.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListPage(naveController: NavController){
    lateinit var mUserViewModel: UserViewModel
    mUserViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(UserViewModel::class.java)

    val dataStore = DataStoreClass(LocalContext.current)
    val savedId = dataStore.getId.collectAsState(initial = -1)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.map_peru),
            contentDescription = "Fondo de pantalla",
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            colorFilter = ColorFilter.tint(LGray)
        )

        var userName by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            userName = mUserViewModel.getUserName(savedId.value!!) ?: ""
        }

        lateinit var mPotholeViewModel: PotholeViewModel
        mPotholeViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(PotholeViewModel::class.java)



        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = DarkOrange, // Color de fondo de la barra superior
            contentColor = Color.White, // Color del contenido de la barra superior

            navigationIcon = {
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            dataStore.clearId()
                        }
                        naveController.navigate(Screens.Login.route)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Exit Icon"
                        )
                    }
                )
            },

            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                ){
                    Text(
                        text = userName,
                        modifier = Modifier
                            .padding(end = 10.dp),
                    )
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Icon",
                    )
                }
            }
        )

        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                mPotholeViewModel.addPothole(PotholeEntity(potUser = savedId.value!!, potSev = 2, potDesc = "des"))
            }) {
                Text(
                    text = "INGRESAR",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}