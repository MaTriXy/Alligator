package me.aartikov.flowscreenswitchersample

import android.app.Application
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.GeneratedNavigationFactory
import me.aartikov.alligator.navigationfactories.NavigationFactory

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sAndroidNavigator = AndroidNavigator(GeneratedNavigationFactory())
    }

    companion object {
        private lateinit var sAndroidNavigator: AndroidNavigator

        val navigator: Navigator
            get() = sAndroidNavigator

        val navigationFactory: NavigationFactory
            get() = sAndroidNavigator.navigationFactory

        val navigationContextBinder: NavigationContextBinder
            get() = sAndroidNavigator

        val screenResolver: ScreenResolver
            get() = sAndroidNavigator.screenResolver
    }
}
