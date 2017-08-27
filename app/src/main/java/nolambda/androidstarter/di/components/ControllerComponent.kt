package nolambda.androidstarter.di.components

import dagger.Subcomponent
import nolambda.androidstarter.di.modules.ControllerModule
import nolambda.androidstarter.screens.ApiExampleController
import nolambda.androidstarter.screens.DetailController
import nolambda.androidstarter.screens.MainController

@Subcomponent(modules = arrayOf(ControllerModule::class))
interface ControllerComponent {

    /* --------------------------------------------------- */
    /* > Injects */
    /* --------------------------------------------------- */

    fun inject(mainController: MainController)
    fun inject(detailController: DetailController)
    fun inject(apiExampleController: ApiExampleController)

    /* --------------------------------------------------- */
    /* > Builders */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun controllerModule(controllerModule: ControllerModule): ControllerComponent.Builder
        fun build(): ControllerComponent
    }
}
