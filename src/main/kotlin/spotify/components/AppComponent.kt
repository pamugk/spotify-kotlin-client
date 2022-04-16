package spotify.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import spotify.api.SpotifyOAuth2Client
import spotify.components.widgets.*

interface App {
    val routerState: Value<RouterState<*, Page>>

    val navbar: Navbar
    val player: Player
    val drawer: Drawer

    sealed interface Page {
        data class Main(val component: MainPage): Page
        data class Search(val component: SearchPage): Page

        data class Album(val component: AlbumPage): Page
        data class Artist(val component: ArtistPage): Page
        data class Episode(val component: EpisodePage): Page
        data class Playlist(val component: PlaylistPage): Page
        data class Show(val component: ShowPage): Page
        data class User(val component: UserPage): Page
    }
}

class AppComponent(
    componentContext: ComponentContext
) : App, ComponentContext by componentContext {
    private val api = SpotifyOAuth2Client(Json.decodeFromStream(this::class.java.getResourceAsStream("/settings.json")!!))

    private val router = router<Config, App.Page>(
        initialConfiguration = Config.Main,
        childFactory = ::child
    )

    private val _authorizedValue = MutableValue(false)
    private val authorized = _authorizedValue

    init {
        api.authenticationStatusChanged += { authorized -> _authorizedValue.reduce { authorized } }
    }

    override val routerState: Value<RouterState<*, App.Page>> = router.state

    override val navbar = NavbarComponent(api, authorized, childContext("Navbar"))
    override val player = PlayerComponent(api, childContext("Player"))
    override val drawer = DrawerComponent(
        childContext("Drawer"),
        { checkedPush(Config.Main) },
        { checkedPush(Config.Search("")) },
        {},
        {},
        { checkedPush(Config.Playlist(it)) },
        {}
    )

    private fun checkedPush(newConfig: Config) {
        if (routerState.value.activeChild.configuration != newConfig) {
            router.replaceCurrent(newConfig)
        }
    }

    private fun child(config: Config, componentContext: ComponentContext): App.Page =
        when (config) {
            is Config.Main -> App.Page.Main(main(componentContext))
            is Config.Search -> App.Page.Search(search(componentContext))

            is Config.Album -> App.Page.Album(album(componentContext))
            is Config.Artist -> App.Page.Artist(artist(componentContext))
            is Config.Episode -> App.Page.Episode(episode(componentContext))
            is Config.Playlist -> App.Page.Playlist(playlist(componentContext))
            is Config.Show -> App.Page.Show(show(componentContext))
            is Config.User -> App.Page.User(user(componentContext))
        }

    private fun main(componentContext: ComponentContext): MainPage =
        MainPageComponent(componentContext)

    private fun search(componentContext: ComponentContext): SearchPage =
        SearchPageComponent(componentContext)

    private fun album(componentContext: ComponentContext): AlbumPage =
        AlbumPageComponent(componentContext)

    private fun artist(componentContext: ComponentContext): ArtistPage =
        ArtistPageComponent(componentContext)

    private fun episode(componentContext: ComponentContext): EpisodePage =
        EpisodePageComponent(componentContext)

    private fun playlist(componentContext: ComponentContext): PlaylistPage =
        PlaylistPageComponent(componentContext)

    private fun show(componentContext: ComponentContext): ShowPage =
        ShowPageComponent(componentContext)

    private fun user(componentContext: ComponentContext): UserPage =
        UserPageComponent(componentContext)

    private sealed interface Config: Parcelable {
        @Parcelize
        object Main: Config

        @Parcelize
        data class Search(val query: String): Config

        @Parcelize
        data class Album(val id: Long): Config

        @Parcelize
        data class Artist(val id: Long): Config

        @Parcelize
        data class Episode(val id: Long): Config

        @Parcelize
        data class Playlist(val id: Long): Config

        @Parcelize
        data class Show(val id: Long): Config

        @Parcelize
        data class User(val id: Long): Config
    }
}