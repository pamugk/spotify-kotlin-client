package spotify.components.widgets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import spotify.api.SpotifyOAuth2Client
import spotify.api.domain.User
import spotify.api.service.getCurrentUser
import java.awt.Desktop
import java.net.URI

interface Navbar {
    val authorizationState: Value<Boolean>

    fun logIn()

    suspend fun getUser(): User

    fun logOut()
}

class NavbarComponent(
    private val api: SpotifyOAuth2Client,
    override val authorizationState: Value<Boolean>,
    componentContext: ComponentContext
): Navbar, ComponentContext by componentContext  {
    override fun logIn() = Desktop.getDesktop().browse(URI.create(api.loginUrl))

    override suspend fun getUser(): User = api.getCurrentUser()

    override fun logOut() = api.logOut()
}