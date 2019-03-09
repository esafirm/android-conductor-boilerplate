package nolambda.androidstarter.di.components

import com.bluelinelabs.conductor.Controller
import dagger.BindsInstance
import dagger.Subcomponent
import nolambda.androidstarter.di.modules.ControllerModule
import nolambda.androidstarter.di.plugins.PluginManager
import nolambda.androidstarter.screens.ApiExampleStatefulScreen
import nolambda.androidstarter.screens.DetailScreen
import nolambda.androidstarter.screens.MainScreen

@Subcomponent(modules = [ControllerModule::class])
interface ControllerComponent {

    /* --------------------------------------------------- */
    /* > Injects */
    /* --------------------------------------------------- */

    fun inject(mainController: MainScreen)
    fun inject(detailController: DetailScreen)
    fun inject(apiExampleController: ApiExampleStatefulScreen)

    /* --------------------------------------------------- */
    /* > Builders */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindController(controller: Controller) : ControllerComponent.Builder

        @BindsInstance
        fun bindPluginManager(pluginManager: PluginManager): ControllerComponent.Builder

        fun controllerModule(controllerModule: ControllerModule): ControllerComponent.Builder

        fun build(): ControllerComponent
    }
}
