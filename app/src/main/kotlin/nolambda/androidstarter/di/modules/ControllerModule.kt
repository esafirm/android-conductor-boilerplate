package nolambda.androidstarter.di.modules

import dagger.Module
import nolambda.androidstarter.screens.DetailModule

@Module(includes = [DetailModule::class])
class ControllerModule

