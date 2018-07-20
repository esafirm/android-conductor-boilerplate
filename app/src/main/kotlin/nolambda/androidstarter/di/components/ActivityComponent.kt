package nolambda.androidstarter.di.components

import dagger.Subcomponent
import nolambda.androidstarter.di.modules.ActivityModule
import nolambda.androidstarter.di.modules.NavigatorModule
import nolambda.androidstarter.di.scopes.ActivityScope
import nolambda.androidstarter.navigator.AppNavigator
import nolambda.androidstarter.navigator.IntentNavigator

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, NavigatorModule::class])
interface ActivityComponent {

    fun appNavigator(): AppNavigator
    fun intentNavigator(): IntentNavigator

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun controllerComponent(): ControllerComponent.Builder

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityComponent.Builder
        fun navigatorModule(navigatorModule: NavigatorModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }
}
