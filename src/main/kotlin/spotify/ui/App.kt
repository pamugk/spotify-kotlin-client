package spotify.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import spotify.components.App
import spotify.ui.pages.*
import spotify.ui.widgets.Drawer
import spotify.ui.widgets.NavbarUi
import spotify.ui.widgets.PlayerUi
import spotify.ui.widgets.SearchbarUi

@Composable
@Preview
fun AppUI(component: App) {
    val route = component.routerState.subscribeAsState()

    MaterialTheme {
        Column {
            Row(Modifier.weight(1f)) {
                Drawer(component.drawer)
                Column {
                    NavbarUi(component.navbar) {
                        if (route.value.activeChild.instance is App.Page.Search) {
                            SearchbarUi(component.searchbar)
                        }
                    }
                    Children(component.routerState) {
                        when (val child = it.instance) {
                            is App.Page.Main -> MainPageUi(child.component)
                            is App.Page.Search -> SearchPageUi(child.component)
                            is App.Page.Album -> AlbumPageUi(child.component)
                            is App.Page.Artist -> ArtistPageUi(child.component)
                            is App.Page.Episode -> EpisodePageUi(child.component)
                            is App.Page.Playlist -> PlaylistPageUi(child.component)
                            is App.Page.Show -> ShowPageUi(child.component)
                            is App.Page.User -> UserPageUi(child.component)
                        }
                    }
                }
            }
            PlayerUi(component.player)
        }
    }
}