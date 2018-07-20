package nolambda.androidstarter.navigator

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.esafirm.conductorextra.setRootIfNeeded
import com.esafirm.conductorextra.transaction.Routes
import nolambda.androidstarter.screens.ApiExampleStatefulScreen
import nolambda.androidstarter.screens.DetailScreen
import nolambda.androidstarter.screens.MainScreen

class AppNavigator(private val router: Router,
                   private val overlayRouter: Router) {

    fun setupContent() {
        router.setRootIfNeeded(MainScreen())
    }

    fun goToDetail() {
        router.pushController(Routes.simpleTransaction(
                DetailScreen(),
                HorizontalChangeHandler()
        ))
    }

    fun goToApiExample() {
        router.pushController(Routes.simpleTransaction(
                ApiExampleStatefulScreen(),
                VerticalChangeHandler()
        ))
    }
}
