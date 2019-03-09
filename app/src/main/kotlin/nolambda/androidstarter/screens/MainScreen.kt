package nolambda.androidstarter.screens

import com.esafirm.conductorextra.common.onEvent
import kotlinx.android.synthetic.main.controller_main.*
import nolambda.androidstarter.R
import nolambda.androidstarter.commons.AbsScreen
import nolambda.androidstarter.commons.makeComponent
import nolambda.androidstarter.navigator.AppNavigator
import javax.inject.Inject

class MainScreen : AbsScreen() {

    @Inject lateinit var navigator: AppNavigator

    init {
        onEvent(onPostCreateView = { remover ->
            makeComponent().inject(this)
            remover()
        })
    }

    override fun createView() = xml(R.layout.controller_main)

    override fun render() {
        main_txt_hello.setOnClickListener {
            navigator.goToDetail()
        }
    }
}
