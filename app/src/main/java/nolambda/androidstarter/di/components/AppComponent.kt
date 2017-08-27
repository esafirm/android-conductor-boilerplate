package nolambda.androidstarter.di.components

import android.app.Application
import dagger.Component
import nolambda.androidstarter.di.modules.AppModule
import nolambda.androidstarter.di.modules.ContextServiceModule
import nolambda.androidstarter.di.modules.NetworkModule
import nolambda.androidstarter.network.ApiService
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ContextServiceModule::class,
        NetworkModule::class
))
interface AppComponent {

    /* --------------------------------------------------- */
    /* > Singletons */
    /* --------------------------------------------------- */

    fun apiService(): ApiService

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun activityComponent(): ActivityComponent.Builder

    companion object {
        fun initialize(app: Application): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule((app)))
                        .build()
    }
}
