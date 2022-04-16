package spotify.components.widgets

import com.arkivanov.decompose.ComponentContext

interface Drawer {
    fun navigateToMain()
    fun navigateToSearch()
    fun navigateToLibrary()
    fun navigateToNewPlaylist()
    fun navigateToPlaylist(id: Long)
    fun navigateToFavourite()
}

class DrawerComponent(
    componentContext: ComponentContext,
    private val onNavigateToMain: () -> Unit = {},
    private val onNavigateToSearch: () -> Unit = {},
    private val onNavigateToLibrary: () -> Unit = {},
    private val onNavigateToNewPlaylist: () -> Unit = {},
    private val onNavigateToPlaylist: (id: Long) -> Unit = {},
    private val onNavigateToFavourite: () -> Unit = {}
): Drawer, ComponentContext by componentContext {
    override fun navigateToMain() = onNavigateToMain()
    override fun navigateToSearch() = onNavigateToSearch()
    override fun navigateToLibrary() = onNavigateToLibrary()
    override fun navigateToNewPlaylist() = onNavigateToNewPlaylist()
    override fun navigateToPlaylist(id: Long) = onNavigateToPlaylist(id)
    override fun navigateToFavourite() = onNavigateToFavourite()
}