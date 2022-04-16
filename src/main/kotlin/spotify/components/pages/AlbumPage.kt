package spotify.components

import com.arkivanov.decompose.ComponentContext

interface AlbumPage

class AlbumPageComponent(
    componentContext: ComponentContext
): AlbumPage, ComponentContext by componentContext