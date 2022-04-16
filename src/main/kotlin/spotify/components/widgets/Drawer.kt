package spotify.components.widgets

import com.arkivanov.decompose.ComponentContext

interface Drawer

class DrawerComponent(
    componentContext: ComponentContext
): Drawer, ComponentContext by componentContext