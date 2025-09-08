package com.heckofanapp.storybook

enum class NavigationRoute {
    Search,
}

sealed class NavigationItem(
    val route: NavigationRoute,
) {
    data object Search : NavigationItem(
        route = NavigationRoute.Search,
    )
}
