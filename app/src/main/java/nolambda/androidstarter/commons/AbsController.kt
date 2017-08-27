package nolambda.androidstarter.commons

import com.esafirm.conductorextra.butterknife.BinderController
import nolambda.androidstarter.di.components.ActivityComponent
import nolambda.androidstarter.di.components.ControllerComponent
import nolambda.androidstarter.di.helpers.HasComponent
import nolambda.androidstarter.di.modules.ControllerModule

abstract class AbsController : BinderController() {

    @Suppress("UNCHECKED_CAST")
    protected val component: ControllerComponent by lazy {
        if (activity == null) {
            throw IllegalStateException("Not attached to Activity")
        }
        activity.let { it as HasComponent<ActivityComponent> }
                .getComponent()
                .controllerComponent()
                .controllerModule(ControllerModule(this))
                .build()
    }
}
