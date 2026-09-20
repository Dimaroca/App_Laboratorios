package plat.labs.rickymorty.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import plat.labs.rickymorty.R

@Composable
fun Login(onNavigateToCharacters: () -> Unit){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Rick y Morty",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onNavigateToCharacters,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Empezar")
            }
        }

        Text(
            text = "Diego Mateo Rodríguez Carrillo #25215",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )

    }
}