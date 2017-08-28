package nolambda.androidstarter.navigator

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.esafirm.conductorextra.transaction.Routes
import nolambda.androidstarter.screens.ApiExampleController
import nolambda.androidstarter.screens.DetailController
import nolambda.androidstarter.screens.MainController

class AppNavigator(private val router: Router,
                   private val overlayRouter: Router) {

    fun setupContent() {
        router.setRoot(RouterTransaction.with(MainController()))
    }

    fun goToDetail() {
        router.pushController(Routes.simpleTransaction(
                DetailController(),
                HorizontalChangeHandler()
        ))
    }

    fun goToApiExample() {
        router.pushController(Routes.simpleTransaction(
                ApiExampleController(),
                VerticalChangeHandler()
        ))
    }
}
