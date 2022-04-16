package spotify.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.font.FontWeight
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import spotify.api.domain.User
import spotify.components.widgets.Navbar
import spotify.ui.resources.fontAwesomeFamily
import spotify.ui.util.AsyncImage
import spotify.ui.util.loadImageBitmapAsync

@Composable
inline fun NavbarUi(
    component: Navbar,
    modifier: Modifier = Modifier,
    secondaryContent: @Composable () -> Unit
) {
    val loggedIn = component.authorizationState.subscribeAsState()
    val user = remember { mutableStateOf<User?>(null) }

    Row(modifier = modifier) {
        TextButton(onClick = {}, enabled = false){
            Text("angle-left", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
        }
        TextButton(onClick = {}, enabled = false){
            Text("angle-right", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
        }
        secondaryContent()
        Spacer(Modifier.weight(1f))

        if (loggedIn.value) {
            if (user.value == null) {
                LaunchedEffect(true) {
                    user.value = component.getUser()
                }

                Button(onClick = {
                    component.logOut()
                }){
                    CircularProgressIndicator()
                }
            } else {
                Button(onClick = {
                    component.logOut()
                }){
                    if (user.value!!.images.isNotEmpty()) {
                        AsyncImage(
                            { loadImageBitmapAsync(user.value!!.images.last().url) },
                            { remember { BitmapPainter(it) } },
                            user.value!!.displayedName.orEmpty()
                        )
                    }
                    Text(user.value!!.displayedName.orEmpty())
                }
            }
        } else {
            Button(onClick = {
                component.logIn()
            }){
                Text("Вход")
            }
        }
    }
}