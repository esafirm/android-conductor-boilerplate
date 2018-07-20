package nolambda.androidstarter.di.components

import dagger.Subcomponent
import nolambda.androidstarter.di.modules.ControllerModule
import nolambda.androidstarter.screens.ApiExampleStatefulScreen
import nolambda.androidstarter.screens.DetailScreen
import nolambda.androidstarter.screens.MainScreen

@Subcomponent(modules = arrayOf(ControllerModule::class))
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
        fun controllerModule(controllerModule: ControllerModule): ControllerComponent.Builder
        fun build(): ControllerComponent
    }
}
