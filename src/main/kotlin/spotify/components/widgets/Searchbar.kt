package spotify.components.widgets

import com.arkivanov.decompose.ComponentContext

interface Searchbar {
    fun doSearch(query: String)
}

class SearchbarComponent(
    componentContext: ComponentContext,
    private val onDoSearch: (String) -> Unit = {}
): Searchbar, ComponentContext by componentContext {
    override fun doSearch(query: String) = onDoSearch(query)
}