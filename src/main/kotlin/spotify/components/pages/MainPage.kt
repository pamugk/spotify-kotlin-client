package spotify.components

import com.arkivanov.decompose.ComponentContext

interface MainPage

class MainPageComponent(
    componentContext: ComponentContext
) : MainPage, ComponentContext by componentContext