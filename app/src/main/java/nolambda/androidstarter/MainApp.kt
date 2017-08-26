package nolambda.androidstarter

import android.support.multidex.MultiDexApplication
import nolambda.androidstarter.di.components.AppComponent

class MainApp : MultiDexApplication() {

    val appComponent: AppComponent by lazy {
        AppComponent.intialize(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
