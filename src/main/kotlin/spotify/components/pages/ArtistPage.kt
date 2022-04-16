package spotify.components

import com.arkivanov.decompose.ComponentContext

interface ArtistPage

class ArtistPageComponent(
    componentContext: ComponentContext
): ArtistPage, ComponentContext by componentContext