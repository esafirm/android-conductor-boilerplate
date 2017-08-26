package nolambda.androidstarter.commons

import com.esafirm.conductorextra.butterknife.AbsController
import nolambda.androidstarter.di.components.ControllerComponent
import nolambda.androidstarter.di.modules.ControllerModule

abstract class Controller : AbsController() {

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
