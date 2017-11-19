package nolambda.androidstarter.commons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.internal.LifecycleHandler
import nolambda.androidstarter.MainApp
import nolambda.androidstarter.di.components.ActivityComponent
import nolambda.androidstarter.di.helpers.HasComponent
import nolambda.androidstarter.di.modules.ActivityModule
import nolambda.androidstarter.di.modules.NavigatorModule
import nolambda.androidstarter.navigator.AppNavigator


class MainActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    companion object {
        const val LAYOUT_ID_MAIN = 0x1
        const val LAYOUT_ID_OVERLAY = 0x2
    }

    private lateinit var router: Router
    private lateinit var overlayRouter: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        val main = generateLayout(LAYOUT_ID_MAIN, params)
        val overlay = generateLayout(LAYOUT_ID_OVERLAY, params)

        setContentView(FrameLayout(this).apply {
            layoutParams = params
            addView(main)
            addView(overlay)
        })

        val lifecycleHandler = LifecycleHandler.install(this)
        router = lifecycleHandler.getRouter(main, savedInstanceState)
        overlayRouter = lifecycleHandler.getRouter(overlay, savedInstanceState)

        router.rebindIfNeeded()
        overlayRouter.rebindIfNeeded()

        activityComponent.appNavigator().setupContent()
    }

    private fun generateLayout(id: Int, params: FrameLayout.LayoutParams) =
            ChangeHandlerFrameLayout(this).apply {
                setId(id)
                layoutParams = params
            }


    override fun onBackPressed() {
        if (!overlayRouter.handleBack() && !router.handleBack()) {
            super.onBackPressed()
        }
    }

    /* --------------------------------------------------- */
    /* > Component */
    /* --------------------------------------------------- */

    override fun getComponent(): ActivityComponent = activityComponent

    private val activityComponent: ActivityComponent by lazy {
        MainApp.appComponent!!
                .activityComponent()
                .activityModule(ActivityModule(this))
                .navigatorModule(NavigatorModule(AppNavigator(router, overlayRouter)))
                .build()
    }
}
