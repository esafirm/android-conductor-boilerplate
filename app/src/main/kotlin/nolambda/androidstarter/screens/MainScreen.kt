package nolambda.androidstarter.screens

import com.esafirm.conductorextra.common.onEvent
import kotlinx.android.synthetic.main.controller_main.*
import nolambda.androidstarter.R
import nolambda.androidstarter.commons.AbsScreen
import nolambda.androidstarter.navigator.AppNavigator
import javax.inject.Inject

class MainScreen : AbsScreen() {

    init {
        onEvent(onPostCreateView = { remover ->
            component.inject(this)
            remover()
        })
        screenView = xml(R.layout.controller_main)
    }

    @Inject lateinit var navigator: AppNavigator

    override fun render() {
        main_txt_hello.setOnClickListener {
            navigator.goToDetail()
        }
    }
}
