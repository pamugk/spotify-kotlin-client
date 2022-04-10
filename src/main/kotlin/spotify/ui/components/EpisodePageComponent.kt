package spotify.ui.components

import com.arkivanov.decompose.ComponentContext

interface EpisodePage

class EpisodePageComponent(
    componentContext: ComponentContext
): EpisodePage, ComponentContext by componentContext