package nolambda.androidstarter.di.modules

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import nolambda.androidstarter.di.qualifiers.ActivityContext

@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity
}
