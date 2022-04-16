package spotify.components

import com.arkivanov.decompose.ComponentContext

interface UserPage

class UserPageComponent(
    componentContext: ComponentContext
): UserPage, ComponentContext by componentContext