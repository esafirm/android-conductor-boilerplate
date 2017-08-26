package nolambda.androidstarter.screens

import android.view.View
import nolambda.androidstarter.R
import nolambda.androidstarter.commons.AbsController

class DetailController : AbsController() {

    override fun getLayoutResId(): Int = R.layout.controller_detail

    override fun onViewBound(bindingResult: View) {
    }
}
