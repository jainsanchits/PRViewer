package com.sjain.prviewer.di.component

import com.sjain.prviewer.di.module.PullRequestActivityModule
import com.sjain.prviewer.ui.activity.PullRequestActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [PullRequestActivityModule::class])
interface PullRequestActivityComponent : AndroidInjector<PullRequestActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PullRequestActivity>()
}
