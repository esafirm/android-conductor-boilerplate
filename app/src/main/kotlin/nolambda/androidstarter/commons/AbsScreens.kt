package nolambda.androidstarter.commons

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.esafirm.conductorextra.common.onEvent
import nolambda.androidstarter.di.components.ActivityComponent
import nolambda.androidstarter.di.components.ControllerComponent
import nolambda.androidstarter.di.helpers.HasComponent
import nolambda.androidstarter.di.modules.ControllerModule
import nolambda.androidstarter.di.plugins.Plugin
import nolambda.androidstarter.di.plugins.PluginManager
import nolambda.screen.Presenter
import nolambda.screen.Screen
import nolambda.screen.StatefulScreen

typealias InitBlock = () -> Unit

abstract class AbsStatefulScreen<S, P : Presenter<S>> : StatefulScreen<S, P> {
    protected var onInit: InitBlock? = null
    private val initLazy by lazy { onInit?.invoke() }

    constructor() : super()
    constructor(bundle: Bundle?) : super(bundle)

    init {
        onEvent(onPreContextAvailable = { remover ->
            initLazy
            remover()
        })
    }
}

abstract class AbsScreen : Screen {
    protected var onInit: InitBlock? = null
    private val initLazy by lazy { onInit?.invoke() }

    constructor() : super()
    constructor(bundle: Bundle?) : super(bundle)

    init {
        onEvent(onPreContextAvailable = { remover ->
            initLazy
            remover()
        })
    }
}

@Suppress("UNCHECKED_CAST")
internal fun Controller.makeComponent(vararg plugins: Plugin): ControllerComponent {
    if (activity == null) {
        throw IllegalStateException("Not attached to Activity")
    }
    if ((activity is HasComponent<*>).not()) {
        throw IllegalStateException("Activity should implement HasComponent<Component>")
    }
    return activity.let { it as HasComponent<ActivityComponent> }
            .getComponent()
            .controllerComponent()
            .bindController(this)
            .bindPluginManager(PluginManager(*plugins))
            .controllerModule(ControllerModule())
            .build()
}



