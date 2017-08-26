package nolambda.androidstarter.di.components

import android.app.Application
import dagger.Component
import nolambda.androidstarter.di.modules.AppModule
import nolambda.androidstarter.di.modules.ContextServiceModule

@Component(modules = arrayOf(
        AppModule::class,
        ContextServiceModule::class
))
interface AppComponent {

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun activityComponent(): ActivityComponent.Builder

    companion object {
        fun intialize(app: Application) =
                DaggerAppComponent.builder()
                        .appModule(AppModule((app)))
                        .build()
    }
}
