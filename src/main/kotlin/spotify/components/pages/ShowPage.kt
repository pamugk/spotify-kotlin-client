package spotify.components

import com.arkivanov.decompose.ComponentContext

interface ShowPage

class ShowPageComponent(
    componentContext: ComponentContext
): ShowPage, ComponentContext by componentContext