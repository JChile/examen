package com.example.danp_examen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.example.danp_examen.R
import com.example.danp_examen.ui.theme.DarkOrange
import com.example.danp_examen.ui.theme.LGray
import com.example.danp_examen.viewmodel.UserViewModel

@Composable
fun ListPage(naveController: NavController, id: Int){
    lateinit var mUserViewModel: UserViewModel
    mUserViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(UserViewModel::class.java)

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
            userName = mUserViewModel.getUserName(id) ?: ""
        }

        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = DarkOrange, // Color de fondo de la barra superior
            contentColor = Color.White, // Color del contenido de la barra superior

            navigationIcon = {
                IconButton(
                    onClick = {  },
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
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
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
    }
}