// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import spotify.ui.AppUI
import spotify.components.AppComponent

fun main() {
    val lifecycle = LifecycleRegistry()
    val root = AppComponent(DefaultComponentContext(lifecycle))

    application {
        val icon = painterResource("spotify/icon.svg")
        val trayState = rememberTrayState()

        Tray(
            state = trayState,
            icon = icon,
            menu = {
                Item("Выйти", onClick = ::exitApplication)
            }
        )
        Window(onCloseRequest = ::exitApplication, icon = icon, title = "Неофициальный клиент Spotify") {
            AppUI(root)
        }
    }
}
