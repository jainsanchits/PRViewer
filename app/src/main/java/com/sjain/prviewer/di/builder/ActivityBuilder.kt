package com.sjain.prviewer.di.builder

import android.app.Activity
import com.sjain.prviewer.di.component.MainActivityComponent
import com.sjain.prviewer.di.component.PullRequestActivityComponent
import com.sjain.prviewer.ui.activity.MainActivity
import com.sjain.prviewer.ui.activity.PullRequestActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(PullRequestActivity::class)
    internal abstract fun bindPullRequestActivity(builder: PullRequestActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}