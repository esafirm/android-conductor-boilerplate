package nolambda.androidstarter.screens

import android.view.View
import kotlinx.android.synthetic.main.controller_detail.*
import nolambda.androidstarter.R
import nolambda.androidstarter.commons.AbsStatefulScreen
import nolambda.androidstarter.navigator.AppNavigator
import nolambda.androidstarter.network.ApiService
import nolambda.screen.Presenter
import javax.inject.Inject

data class DetailState(
        val isLoading: Boolean,
        val count: Int = 0
)

class DetailPresenter @Inject constructor(private val apiService: ApiService) : Presenter<DetailState>() {

    override fun initialState(): DetailState = DetailState(isLoading = false, count = 0)

    override fun initPresenter() {
        setLoading(true)
    }

    fun setLoading(isLoading: Boolean) {
        setState {
            it.copy(isLoading = isLoading)
        }
    }

    fun increment() = setState {
        it.copy(count = it.count + 1)
    }
}

class DetailScreen : AbsStatefulScreen<DetailState, DetailPresenter>() {

    @Inject lateinit var navigator: AppNavigator
    @Inject lateinit var presenter: DetailPresenter

    init {
        onInit = {
            component.inject(this)
        }
    }

    override fun createView() = xml(R.layout.controller_detail)
    override fun createPresenter() = presenter

    override fun render(presenter: DetailPresenter, state: DetailState) {
        main_progress.visibility = when (state.isLoading) {
            true -> View.VISIBLE
            false -> View.GONE
        }
        main_txt_yeah.text = "Counter: ${state.count}"
        main_btn_add.setOnClickListener { presenter.increment() }
    }
}
