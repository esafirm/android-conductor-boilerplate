package nolambda.androidstarter.commons

import com.bluelinelabs.conductor.Controller
import com.esafirm.conductorextra.addLifecycleCallback
import nolambda.androidstarter.di.components.ActivityComponent
import nolambda.androidstarter.di.components.ControllerComponent
import nolambda.androidstarter.di.helpers.HasComponent
import nolambda.androidstarter.di.modules.ControllerModule
import nolambda.screen.Presenter
import nolambda.screen.Screen
import nolambda.screen.StatefulScreen

typealias InitBlock = () -> Unit

abstract class AbsStatefulScreen<S, P : Presenter<S>> : StatefulScreen<S, P>() {
    protected val component by lazy { makeComponent() }
    protected var onInit: InitBlock? = null

    init {
        addLifecycleCallback(onPreContextAvailable = { _, remover ->
            onInit?.invoke()
            remover()
        })
    }
}

abstract class AbsScreen : Screen() {
    protected val component by lazy { makeComponent() }
    protected var onInit: InitBlock? = null

    init {
        addLifecycleCallback(onPreContextAvailable = { _, remover ->
            onInit?.invoke()
            remover()
        })
    }
}

@Suppress("UNCHECKED_CAST")
internal fun Controller.makeComponent(): ControllerComponent {
    if (activity == null) {
        throw IllegalStateException("Not attached to Activity")
    }
    if ((activity is HasComponent<*>).not()) {
        throw IllegalStateException("Activity should implement HasComponent<Component>")
    }
    return activity.let { it as HasComponent<ActivityComponent> }
            .getComponent()
            .controllerComponent()
            .controllerModule(ControllerModule(this))
            .build()
}



