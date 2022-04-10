package spotify.ui.components

import com.arkivanov.decompose.ComponentContext

interface PlaylistPage

class PlaylistPageComponent(
    componentContext: ComponentContext
): PlaylistPage, ComponentContext by componentContext