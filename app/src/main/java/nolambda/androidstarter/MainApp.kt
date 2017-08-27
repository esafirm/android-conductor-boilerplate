package nolambda.androidstarter

import android.support.multidex.MultiDexApplication
import nolambda.androidstarter.di.components.AppComponent

class MainApp : MultiDexApplication() {

    companion object {
        var appComponent: AppComponent? = null

        fun setComponent(appComponent: AppComponent) {
            this.appComponent = appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = AppComponent.initialize(this)
    }
}
