package nolambda.androidstarter.commons

import com.esafirm.conductorextra.butterknife.BinderController
import nolambda.androidstarter.di.components.ControllerComponent
import nolambda.androidstarter.di.modules.ControllerModule

abstract class AbsController : BinderController() {

    protected val component: ControllerComponent by lazy {
        if (activity == null) {
            throw IllegalStateException("Not attached to Activity")
        }
        activity.let { it as MainActivity }
                .activityComponent
                .controllerComponent()
                .controllerModule(ControllerModule(this))
                .build()
    }
}
