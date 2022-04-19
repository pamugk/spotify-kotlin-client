package spotify.components

import com.arkivanov.decompose.ComponentContext

interface SearchPage {
    fun isSearching(): Boolean
    fun doSearch()
}

class SearchPageComponent(
    componentContext: ComponentContext,
    private val query: String
) : SearchPage, ComponentContext by componentContext {
    override fun isSearching(): Boolean {
        return query.isNotEmpty()
    }

    override fun doSearch() {

    }
}