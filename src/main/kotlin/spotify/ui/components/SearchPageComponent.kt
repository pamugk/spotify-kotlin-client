package spotify.ui.components

import com.arkivanov.decompose.ComponentContext

interface SearchPage

class SearchPageComponent(
    componentContext: ComponentContext
) : SearchPage, ComponentContext by componentContext