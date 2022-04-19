package spotify.ui.widgets

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import spotify.components.widgets.Searchbar

@Composable
fun SearchbarUi(component: Searchbar) {
    val searchString = remember { mutableStateOf("") }
    TextField(searchString.value, { searchString.value = it })
}