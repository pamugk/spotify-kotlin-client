package spotify.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import spotify.components.App
import spotify.ui.pages.MainPageUi
import spotify.ui.pages.SearchPageUi
import spotify.ui.widgets.Drawer
import spotify.ui.widgets.NavbarUi
import spotify.ui.widgets.PlayerUi

@Composable
@Preview
fun AppUI(component: App) {
    MaterialTheme {
        Column {
            Row(Modifier.weight(1f)) {
                Drawer(component.drawer)
                Column {
                    NavbarUi(component.navbar) {}
                    Children(component.routerState) {
                        when (val child = it.instance) {
                            is App.Page.Main -> MainPageUi(child.component)
                            is App.Page.Search -> SearchPageUi(child.component)
                            is App.Page.Album -> TODO()
                            is App.Page.Artist -> TODO()
                            is App.Page.Episode -> TODO()
                            is App.Page.Playlist -> TODO()
                            is App.Page.Show -> TODO()
                            is App.Page.User -> TODO()
                        }
                    }
                }
            }
            PlayerUi(component.player)
        }
    }
}