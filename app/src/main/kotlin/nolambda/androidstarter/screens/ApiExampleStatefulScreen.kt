package nolambda.androidstarter.screens

import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.controller_main.*
import nolambda.androidstarter.R
import nolambda.androidstarter.commons.AbsScreen
import nolambda.androidstarter.commons.makeComponent
import nolambda.androidstarter.network.ApiService
import javax.inject.Inject

class ApiExampleStatefulScreen : AbsScreen() {

    @Inject lateinit var apiService: ApiService

    init {
        onInit = {  makeComponent().inject(this) }
    }

    override fun createView() = xml(R.layout.controller_main)

    override fun render() {
        main_txt_hello.setOnClickListener {
            apiService.getUserAgent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { (userAgent), _ ->
                        main_txt_hello.text = userAgent
                    }
        }
    }
}
